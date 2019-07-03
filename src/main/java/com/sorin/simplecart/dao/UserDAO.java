package com.sorin.simplecart.dao;

import com.sorin.simplecart.bean.Permission;
import com.sorin.simplecart.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * userDAO
 *
 * @author LSD
 * @date 2019/06/12
 **/
public interface UserDAO extends JpaRepository<User, String> {

    /**
     * 名称查询
     *
     * @param name
     * @return com.sorin.simplecart.bean.User
     * @author LSD
     * @date 2019/7/2
     */
    User findByNameEquals(String name);

    /**
     * 获取用户权限
     *
     * @param id
     * @return java.util.List<java.util.Map>
     * @author LSD
     * @date 2019/6/24
     */
    @Query(nativeQuery = true,
            value = "select d.* from user_ a left join user_role b on a.id = b.user_id left join role_permission c on b.role_id = c.role_id left join permission_ d on c.permission_id = d.id where a.id = ?")
    List<Permission> getPermission(String id);

}
