package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.Permission;
import com.sorin.simplecart.dao.PermissionDAO;
import com.sorin.simplecart.service.api.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return permissionDAO.findAll();
    }

    @Override
    public Permission selectByPrimaryKey(String id) {
        Optional<Permission> op = permissionDAO.findById(id);
        if (op.isPresent()) {
            return op.get();
        } else {
            return null;
        }
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
        List<Permission> all = permissionDAO.findAll();
        if (null != all && all.size() > 0) {
            for (Permission permission : all) {
                if (requestURI.equals(permission.getUrl())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<String> listPermissibleURLs(String userName) {
        return permissionDAO.listPermissibleURLs(userName);
    }
}
