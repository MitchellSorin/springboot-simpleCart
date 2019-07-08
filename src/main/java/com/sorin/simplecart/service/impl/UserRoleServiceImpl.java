package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.userrole.UserRole;
import com.sorin.simplecart.dao.UserRoleDAO;
import com.sorin.simplecart.service.api.UserRoleService;
import com.sorin.simplecart.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户角色serviceImpl
 *
 * @author LSD
 * @date 2019/06/20
 **/
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    public List<UserRole> selectByUserId(String userId) {
        if (RedisUtils.hasKey("user_role:user_id:" + userId)) {
            return (List<UserRole>) RedisUtils.get("user_role:user_id:" + userId);
        }
        List<UserRole> userRoles = userRoleDAO.findByUserIdEquals(userId);
        RedisUtils.set("user_role:user_id:" + userId, userRoles, 1800);
        return userRoles;
    }

    @Override
    public List<UserRole> selectByRoleId(String roleId) {
        if (RedisUtils.hasKey("user_role:role_id:" + roleId)) {
            return (List<UserRole>) RedisUtils.get("user_role:role_id:" + roleId);
        }
        List<UserRole> userRoles = userRoleDAO.findByRoleIdEquals(roleId);
        RedisUtils.set("user_role:role_id:" + roleId, userRoles, 1800);
        return userRoles;
    }

    @Override
    public void add(UserRole userRole) {
        userRoleDAO.saveAndFlush(userRole);
        RedisUtils.del("user_role:user_id:" + userRole.getUserId(),
                "user_role:role_id:" + userRole.getRoleId(),
                "user:user_permission_userId:" + userRole.getUserId(),
                "permission:user_url:" + userRole.getUserId());
    }

    @Override
    public void delete(UserRole userRole) {
        userRoleDAO.delete(userRole);
        RedisUtils.del("user_role:user_id:" + userRole.getUserId(),
                "user_role:role_id:" + userRole.getRoleId(),
                "user:user_permission_userId:" + userRole.getUserId(),
                "permission:user_url:" + userRole.getUserId());
    }
}
