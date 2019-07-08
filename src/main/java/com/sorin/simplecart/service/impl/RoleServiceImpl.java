package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.Role;
import com.sorin.simplecart.dao.RoleDAO;
import com.sorin.simplecart.service.api.RoleService;
import com.sorin.simplecart.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 角色serviceImpl
 *
 * @author LSD
 * @date 2019/06/20
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public List<Role> selectAll() {
        if (RedisUtils.hasKey("role:all")) {
            return (List<Role>) RedisUtils.get("role:all");
        }
        List<Role> all = roleDAO.findAll();
        RedisUtils.set("role:all", all, 1800);
        return all;
    }

    @Override
    public Role selectByPrimaryKey(String id) {
        if (RedisUtils.hasKey("role:role_id:" + id)) {
            return (Role) RedisUtils.get("role:role_id:" + id);
        }
        Optional<Role> op = roleDAO.findById(id);
        Role role = op.orElse(null);
        RedisUtils.set("role:role_id:" + id, role, 1800);
        return role;
    }

    @Override
    public List<Role> selectByName(String name) {
        List<Role> all = selectAll();
        List<Role> list = new ArrayList<>();
        if (null != all && all.size() > 0) {
            for (Role role : all) {
                if (role.getName().contains(name)) {
                    list.add(role);
                }
            }
        }
        return list;
    }

    @Override
    public void add(Role role) {
        roleDAO.saveAndFlush(role);
        RedisUtils.del("role:all");
    }

    @Override
    public void delete(Role role) {
        roleDAO.delete(role);
        RedisUtils.del("role:all", "role:role_id:" + role.getId());
        RedisUtils.delByRegex("user_role:user_id:*");
        RedisUtils.del("user_role:role_id:" + role.getId());
        RedisUtils.delByRegex("user:user_permission_userId:*");
        RedisUtils.delByRegex("permission:user_url:*");
    }
}
