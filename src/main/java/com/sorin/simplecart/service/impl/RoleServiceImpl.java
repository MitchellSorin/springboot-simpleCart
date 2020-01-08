package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.Role;
import com.sorin.simplecart.dao.RoleDAO;
import com.sorin.simplecart.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return roleDAO.findAll();
    }

    @Override
    public Role selectByPrimaryKey(String id) {
        return roleDAO.findById(id).orElse(null);
    }

    @Override
    public List<Role> selectByName(String name) {
        return roleDAO.findByNameContains(name);
    }

    @Override
    public void add(Role role) {
        roleDAO.saveAndFlush(role);
    }

    @Override
    public void delete(Role role) {
        roleDAO.delete(role);
    }
}
