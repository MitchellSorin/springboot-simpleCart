package com.sorin.simplecart.dao;

import com.sorin.simplecart.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * userDAO
 *
 * @author LSD
 * @date 2019/06/12
 **/
public interface UserDAO extends JpaRepository<User, String> {

    /**
     * id或者name查询
     *
     * @param id   id
     * @param name name
     * @return java.util.List<com.sorin.simplecart.bean.User>
     * @author LSD
     * @date 2019/6/12
     */
    List<User> findByIdOrName(String id, String name);

    /**
     * name模糊查询
     *
     * @param name name片段
     * @return java.util.List<com.sorin.simplecart.bean.User>
     * @author LSD
     * @date 2019/6/12
     */
    List<User> findByNameLike(String name);

    /**
     * 查询多个name
     *
     * @param names 多个name
     * @return java.util.List<com.sorin.simplecart.bean.User>
     * @author LSD
     * @date 2019/6/12
     */
    List<User> findByNameIn(List<String> names);

    /**
     * 原生sql通过id或者name查询
     *
     * @param id   id
     * @param name name
     * @return java.util.List<com.sorin.simplecart.bean.User>
     * @author LSD
     * @date 2019/6/12
     */
    @Query(nativeQuery = true, value = "select * from user where user_id = ? or user_name = ?")
    List<User> selectByIdOrName(String id, String name);


}
