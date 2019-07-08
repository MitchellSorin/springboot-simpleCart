package com.sorin.simplecart.dao;

import com.sorin.simplecart.bean.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 权限dao
 *
 * @author LSD
 * @date 2019/6/20
 */
public interface PermissionDAO extends JpaRepository<Permission, String> {

    List<Permission> findByNameContains(String name);

    @Query(nativeQuery = true,
            value = "select d.url from user_ a, user_role b, role_permission c, permission_ d where a.id = b.user_id and b.role_id = c.role_id and c.permission_id = d.id and a.id = ?")
    List<String> listPermissibleURLs(String userId);
}
