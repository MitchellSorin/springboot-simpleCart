package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.Permission;
import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.dao.UserDAO;
import com.sorin.simplecart.service.api.UserServcie;
import com.sorin.simplecart.utils.Page4Navigator;
import com.sorin.simplecart.utils.StringUtils;
import com.sorin.simplecart.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * userServiceImpl
 *
 * @author LSD
 * @date 2019/06/13
 **/
@Service
public class UserServcieImpl implements UserServcie {
    @Autowired
    private UserDAO userDAO;

    @Override
    public Page4Navigator<User> select(int offset, int limit, String sortField, String orderType, String id, String name) {
        User user = new User();
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
        if (StringUtils.isNotBlank(id)) {
            user.setId(id);
            matcher = matcher.withMatcher("id", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        if (StringUtils.isNotBlank(name)) {
            user.setName(name);
            matcher = matcher.withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        Example<User> example = Example.of(user, matcher);

        if (StringUtils.isBlank(sortField)) {
            sortField = "id";
        }
        if (StringUtils.isBlank(orderType)) {
            orderType = "asc";
        }
        Sort.Direction direction;
        if ("desc".equalsIgnoreCase(orderType)) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }
        String sort;
        switch (sortField) {
            case "id":
            case "name":
                sort = sortField;
                break;
            default:
                sort = "id";
        }
        PageRequest pageRequest = PageRequest.of(offset, limit, direction, sort);
        Page<User> jpaPage = userDAO.findAll(example, pageRequest);
        return new Page4Navigator<>(jpaPage, 5);
    }

    @Override
    public User selectByName(String name) {
        if (RedisUtils.hasKey("user:user_name:" + name)) {
            return (User) RedisUtils.get("user:user_name:" + name);
        }
        User user = userDAO.findByNameEquals(name);
        RedisUtils.set("user:user_name:" + name, user, 1800);
        return user;
    }

    @Override
    public User selectById(String id) {
        if (RedisUtils.hasKey("user:user_id:" + id)) {
            return (User) RedisUtils.get("user:user_id:" + id);
        }
        Optional<User> op = userDAO.findById(id);
        User user = op.orElse(null);
        RedisUtils.set("user:user_id:" + id, user, 1800);
        return user;
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
        RedisUtils.del("user:user_name:" + user.getName(),
                "user:user_id:" + user.getId(),
                "user:user_permission_userId:" + user.getId());
    }

    @Override
    public void add(User user) {
        userDAO.saveAndFlush(user);
    }


    @Override
    public List<Permission> getPermission(String id) {
        List<Permission> permissions = userDAO.getPermission(id);
        RedisUtils.set("user:user_permission_userId:" + id, permissions);
        return permissions;
    }
}
