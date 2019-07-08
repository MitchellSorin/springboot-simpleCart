package com.sorin.simplecart.service.api;

import com.sorin.simplecart.bean.Permission;
import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.utils.Page4Navigator;

import java.util.List;

/**
 * userService
 *
 * @author LSD
 * @date 2019/06/13
 **/
public interface UserServcie {

    /**
     * 分页查询
     *
     * @param offset 页
     * @param limit  每页条数
     * @param sort   排序字段
     * @param order  排序方式
     * @param id     id
     * @param name   name
     * @return org.springframework.data.domain.Page<com.sorin.simplecart.bean.User>
     * @author LSD
     * @date 2019/6/13
     */
    Page4Navigator<User> select(int offset, int limit, String sort, String order, String id, String name);

    /**
     * 名称查询
     *
     * @param name name
     * @return com.sorin.simplecart.bean.User
     * @author LSD
     * @date 2019/7/2
     */
    User selectByName(String name);

    /**
     * id查询
     *
     * @param id
     * @return com.sorin.simplecart.bean.User
     * @author LSD
     * @date 2019/7/5
     */
    User selectById(String id);

    /**
     * 删除
     *
     * @param user user
     * @author LSD
     * @date 2019/6/13
     */
    void delete(User user);

    /**
     * 新增或修改
     *
     * @param user user
     * @author LSD
     * @date 2019/6/13
     */
    void add(User user);

    /**
     * 获取用户权限
     *
     * @param id id
     * @return java.util.List<java.util.Map>
     * @author LSD
     * @date 2019/7/2
     */
    List<Permission> getPermission(String id);

}
