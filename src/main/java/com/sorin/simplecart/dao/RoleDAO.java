package com.sorin.simplecart.dao;

import com.sorin.simplecart.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 角色dao
 *
 * @author LSD
 * @date 2019/06/20
 **/
public interface RoleDAO extends JpaRepository<Role, String> {

    List<Role> findByNameContains(String name);
}
