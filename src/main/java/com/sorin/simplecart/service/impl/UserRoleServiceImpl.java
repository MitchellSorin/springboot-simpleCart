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
        return userRoleDAO.findByUserIdEquals(userId);
    }

    @Override
    public List<UserRole> selectByRoleId(String roleId) {
        return userRoleDAO.findByRoleIdEquals(roleId);
    }

    @Override
    public void add(UserRole userRole) {
        userRoleDAO.saveAndFlush(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        userRoleDAO.delete(userRole);
    }
}
