package com.sorin.simplecart.dao;

import com.sorin.simplecart.bean.userrole.UserRole;
import com.sorin.simplecart.bean.userrole.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户角色dao
 *
 * @author LSD
 * @date 2019/06/20
 **/
public interface UserRoleDAO extends JpaRepository<UserRole, UserRoleKey> {
}
