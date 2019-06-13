package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.User;
import com.sorin.simplecart.dao.UserDAO;
import com.sorin.simplecart.service.api.UserServcie;
import com.sorin.simplecart.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * userServiceImpl
 *
 * @author LSD
 * @date 2019/06/13
 **/
@Service
public class UserServcieImpl implements UserServcie {
    @Autowired
    UserDAO userDAO;

    @Override
    public Page<User> select(int offset, int limit, String sortField, String orderType, String id, String name) {

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
        return userDAO.findAll(example, pageRequest);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public void add(User user) {
        userDAO.save(user);
    }
}
