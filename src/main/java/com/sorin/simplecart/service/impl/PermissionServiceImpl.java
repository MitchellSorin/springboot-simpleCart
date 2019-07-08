package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.Permission;
import com.sorin.simplecart.dao.PermissionDAO;
import com.sorin.simplecart.service.api.PermissionService;
import com.sorin.simplecart.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 权限serviceImpl
 *
 * @author LSD
 * @date 2019/06/20
 **/
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDAO permissionDAO;

    @Override
    public List<Permission> selectAll() {
        if (RedisUtils.hasKey("permission:all")) {
            return (List<Permission>) RedisUtils.get("permission:all");
        }
        List<Permission> all = permissionDAO.findAll();
        RedisUtils.set("permission:all", all);
        return all;
    }

    @Override
    public Permission selectByPrimaryKey(String id) {
        if (RedisUtils.hasKey("permission:permission_id:" + id)) {
            return (Permission) RedisUtils.get("permission:permission_id:" + id);
        }
        Optional<Permission> op = permissionDAO.findById(id);
        Permission permission = op.orElse(null);
        RedisUtils.set("permission:permission_id:" + id, permission, 1800);
        return permission;
    }

    @Override
    public List<Permission> selectByName(String name) {
        List<Permission> permissions = this.selectAll();
        List<Permission> permissionList = new ArrayList<>();
        if (null != permissions && permissions.size() > 0) {
            for (Permission permission : permissions) {
                if (permission.getName().contains(name)) {
                    permissionList.add(permission);
                }
            }
        }
        return permissionList;
    }

    @Override
    public void add(Permission permission) {
        permissionDAO.saveAndFlush(permission);
        RedisUtils.del("permission:all");
        RedisUtils.delByRegex("permission:user_url:*");
    }

    @Override
    public void delete(Permission permission) {
        permissionDAO.delete(permission);
        RedisUtils.del("permission:all");
        RedisUtils.del("permission:permission_id:" + permission.getId());
        RedisUtils.delByRegex("user:user_permission_userId:*");
        RedisUtils.delByRegex("role_permission:role_id:permission_id:*" + permission.getId());
        RedisUtils.delByRegex("permission:user_url:*");
    }

    @Override
    public boolean needInterceptor(String requestURI) {
        List<Permission> all = selectAll();
        if (null != all && all.size() > 0) {
            for (Permission permission : all) {
                if (requestURI.startsWith(permission.getUrl())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<String> listPermissibleURLs(String userId) {
        if (RedisUtils.hasKey("permission:user_url:" + userId)) {
            return (List<String>) RedisUtils.get("permission:user_url:" + userId);
        }
        List<String> list = permissionDAO.listPermissibleURLs(userId);
        RedisUtils.set("permission:user_url:" + userId, list, 1800);
        return list;
    }
}
