package com.sorin.simplecart.dao;

import com.sorin.simplecart.bean.rolepermission.RolePermission;
import com.sorin.simplecart.bean.rolepermission.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * 角色权限dao
 *
 * @author LSD
 * @date 2019/06/20
 **/
public interface RolePermissionDAO extends JpaRepository<RolePermission, RolePermissionKey> {
}
