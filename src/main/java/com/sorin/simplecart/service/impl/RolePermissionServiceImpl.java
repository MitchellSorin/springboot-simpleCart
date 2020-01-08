package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.rolepermission.RolePermission;
import com.sorin.simplecart.dao.RolePermissionDAO;
import com.sorin.simplecart.service.api.RolePermissionService;
import com.sorin.simplecart.utils.StringUtils;
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
        return rolePermissionDAO.findAll(Example.of(rolePermission, matcher));
    }

    @Override
    public void add(RolePermission rolePermission) {
        rolePermissionDAO.saveAndFlush(rolePermission);
    }

    @Override
    public void delete(RolePermission rolePermission) {
        rolePermissionDAO.delete(rolePermission);
    }
}
