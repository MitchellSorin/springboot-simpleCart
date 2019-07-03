package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.Permission;
import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.dao.UserDAO;
import com.sorin.simplecart.service.api.UserServcie;
import com.sorin.simplecart.utils.Page4Navigator;
import com.sorin.simplecart.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * userServiceImpl
 *
 * @author LSD
 * @date 2019/06/13
 **/
@Service
@CacheConfig(cacheNames = "user")
public class UserServcieImpl implements UserServcie {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Cacheable(key = "'user ' + #p0 + '~' + #p1")
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
    @Cacheable(key = "'user name:'+#p0")
    public User selectByName(String name) {
        return userDAO.findByNameEquals(name);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void add(User user) {
        userDAO.saveAndFlush(user);
    }


    @Override
    @Cacheable(key = "'user_permission user_id:' + #p0")
    public List<Permission> getPermission(String id) {
        return userDAO.getPermission(id);
    }
}
