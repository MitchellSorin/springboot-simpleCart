package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.Permission;
import com.sorin.simplecart.dao.PermissionDAO;
import com.sorin.simplecart.service.api.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return permissionDAO.findAll();
    }

    @Override
    public Permission selectByPrimaryKey(String id) {
        return permissionDAO.findById(id).orElse(null);
    }

    @Override
    public List<Permission> selectByName(String name) {
        return permissionDAO.findByNameContains(name);
    }

    @Override
    public void add(Permission permission) {
        permissionDAO.saveAndFlush(permission);
    }

    @Override
    public void delete(Permission permission) {
        permissionDAO.delete(permission);
    }

    @Override
    public boolean needInterceptor(String requestURI) {
        return selectAll().stream().anyMatch((permission -> requestURI.startsWith(permission.getUrl())));
    }

    @Override
    public List<String> listPermissibleURLs(String userId) {
        return permissionDAO.listPermissibleURLs(userId);
    }
}
