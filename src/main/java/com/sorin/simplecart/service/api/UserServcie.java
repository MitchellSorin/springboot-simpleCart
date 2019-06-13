package com.sorin.simplecart.service.api;

import com.sorin.simplecart.bean.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

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
    Page<User> select(int offset, int limit, String sort, String order, String id, String name);

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
}
