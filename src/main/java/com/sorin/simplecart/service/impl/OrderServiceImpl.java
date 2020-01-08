package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.order.Order;
import com.sorin.simplecart.dao.OrderDAO;
import com.sorin.simplecart.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单serviceImpl
 *
 * @author LSD
 * @date 2019/07/04
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public List<Order> selectByUserId(String userId) {
        return orderDAO.findByUserIdEquals(userId);
    }

    @Override
    public List<Order> selectByUserIdEqualsAndItemNameLike(String userId, String itemName) {
        return orderDAO.selectByUserIdEqualsAndItemNameLike(userId, itemName);
    }

    @Override
    public Order selectByUserIdAndItemId(String userId, String itemId) {
        return orderDAO.findByUserIdEqualsAndItemIdEquals(userId, itemId);
    }

    @Override
    public void add(Order order) {
        orderDAO.saveAndFlush(order);
    }

    @Override
    public void delete(Order order) {
        orderDAO.delete(order);
    }
}
