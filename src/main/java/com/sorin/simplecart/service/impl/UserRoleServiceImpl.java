package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.userrole.UserRole;
import com.sorin.simplecart.dao.UserRoleDAO;
import com.sorin.simplecart.service.api.UserRoleService;
import com.sorin.simplecart.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public List<UserRole> selectByUserOrRole(String userId, String roleId) {
        UserRole userRole = new UserRole();
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
        if (StringUtils.isNotBlank(userId)) {
            userRole.setUserId(userId);
            matcher = matcher.withMatcher("user_id", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        if (StringUtils.isNotBlank(roleId)) {
            userRole.setRoleId(roleId);
            matcher = matcher.withMatcher("role_id", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        Example<UserRole> example = Example.of(userRole, matcher);
        return userRoleDAO.findAll(example);
    }

    @Override
    public void add(UserRole userRole) {
        userRoleDAO.save(userRole);
    }

    @Override
    public void delete(UserRole userRole) {
        userRoleDAO.delete(userRole);
    }
}
