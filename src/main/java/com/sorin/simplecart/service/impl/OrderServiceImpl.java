package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.order.Order;
import com.sorin.simplecart.dao.OrderDAO;
import com.sorin.simplecart.service.api.OrderService;
import com.sorin.simplecart.utils.redis.RedisUtils;
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
        if (RedisUtils.hasKey("order:user_id:" + userId)) {
            return (List<Order>) RedisUtils.get("order:user_id:" + userId);
        }
        List<Order> orders = orderDAO.findByUserIdEquals(userId);
        RedisUtils.set("order:user_id:" + userId, orders, 1800);
        return orders;
    }

    @Override
    public List<Order> selectByUserIdAndItemName(String userId, String itemName) {
        if (RedisUtils.hasKey("order:user_id:item_name:" + userId + "~" + itemName)) {
            return (List<Order>) RedisUtils.get("order:user_id:item_name:" + userId + "~" + itemName);
        }
        List<Order> orders = orderDAO.findByUserIdAndItemName(userId, itemName);
        RedisUtils.set("order:user_id:item_name:" + userId + "~" + itemName, orders, 1800);
        return orders;
    }

    @Override
    public Order selectByUserIdAndItemId(String userId, String itemId) {
        if (RedisUtils.hasKey("order:user_id:item_id:" + userId + "~" + itemId)) {
            return (Order) RedisUtils.get("order:user_id:item_id:" + userId + "~" + itemId);
        }
        Order order = orderDAO.findByUserIdEqualsAndItemIdEquals(userId, itemId);
        RedisUtils.set("order:user_id:item_id:" + userId + "~" + itemId, order, 1800);
        return order;
    }

    @Override
    public void add(Order order) {
        RedisUtils.del("order:user_id:" + order.getUserId());
        RedisUtils.delByRegex("order:user_id:item_name:" + order.getUserId() + "*");
        RedisUtils.delByRegex("order:user_id:item_id:" + order.getUserId() + "*");
        orderDAO.saveAndFlush(order);
    }

    @Override
    public void delete(Order order) {
        RedisUtils.del("order:user_id:" + order.getUserId());
        RedisUtils.delByRegex("order:user_id:item_name:" + order.getUserId() + "*");
        RedisUtils.delByRegex("order:user_id:item_id:" + order.getUserId() + "*");
        orderDAO.delete(order);
    }
}
