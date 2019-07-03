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
            value = "select d.url from user_ a left join user_role b on a.id = b.user_id left join role_permission c on b.role_id = c.role_id left join permission_ d on c.permission_id = d.id where a.name = ?")
    List<String> listPermissibleURLs(String userName);
}
