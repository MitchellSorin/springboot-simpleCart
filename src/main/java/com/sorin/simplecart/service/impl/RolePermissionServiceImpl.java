package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.rolepermission.RolePermission;
import com.sorin.simplecart.dao.RolePermissionDAO;
import com.sorin.simplecart.service.api.RolePermissionService;
import com.sorin.simplecart.utils.StringUtils;
import com.sorin.simplecart.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色权限serviceImpl
 *
 * @author LSD
 * @date 2019/06/20
 **/
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionDAO rolePermissionDAO;

    @Override
    public List<RolePermission> selectByRoleOrPermission(String roleId, String permissionId) {
        if (RedisUtils.hasKey("role_permission:role_id:permission_id:" + roleId + "~" + permissionId)) {
            return (List<RolePermission>) RedisUtils.get("role_permission:role_id:permission_id:" + roleId + "~" + permissionId);
        }
        RolePermission rolePermission = new RolePermission();
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        if (StringUtils.isNotBlank(roleId)) {
            rolePermission.setRoleId(roleId);
            matcher = matcher.withMatcher("role_id", ExampleMatcher.GenericPropertyMatchers.exact());
        }
        if (StringUtils.isNotBlank(permissionId)) {
            rolePermission.setPermissionId(permissionId);
            matcher = matcher.withMatcher("permission_id", ExampleMatcher.GenericPropertyMatchers.exact());
        }
        Example<RolePermission> example = Example.of(rolePermission, matcher);
        List<RolePermission> all = rolePermissionDAO.findAll(example);
        RedisUtils.set("role_permission:role_id:permission_id:" + roleId + "~" + permissionId, all, 1800);
        return all;
    }

    @Override
    public void add(RolePermission rolePermission) {
        rolePermissionDAO.saveAndFlush(rolePermission);
        RedisUtils.delByRegex("user:user_permission_userId:*");
        RedisUtils.delByRegex("permission:user_url:*");
    }

    @Override
    public void delete(RolePermission rolePermission) {
        rolePermissionDAO.delete(rolePermission);
        RedisUtils.del("role_permission:role_id:permission_id:" + rolePermission.getRoleId() + "~" + rolePermission.getPermissionId());
        RedisUtils.delByRegex("user:user_permission_userId:*");
        RedisUtils.delByRegex("permission:user_url:*");
    }
}
