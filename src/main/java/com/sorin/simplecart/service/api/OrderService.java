package com.sorin.simplecart.service.api;

import com.sorin.simplecart.bean.order.Order;

import java.util.List;

/**
 * 订单service
 *
 * @author LSD
 * @date 2019/07/04
 **/
public interface OrderService {

    /**
     * 获取用户订单
     *
     * @param userId
     * @return java.util.List<com.sorin.simplecart.bean.order.Order>
     * @author LSD
     * @date 2019/7/4
     */
    List<Order> selectByUserId(String userId);

    /**
     * 获取用户搜索的订单
     *
     * @param userId
     * @param itemName
     * @return java.util.List<com.sorin.simplecart.bean.order.Order>
     * @author LSD
     * @date 2019/7/4
     */
    List<Order> selectByUserIdAndItemName(String userId, String itemName);

    /**
     * userId,itemId搜索
     *
     * @param userId
     * @param itemId
     * @return java.util.List<com.sorin.simplecart.bean.order.Order>
     * @author LSD
     * @date 2019/7/5
     */
    Order selectByUserIdAndItemId(String userId, String itemId);

    /**
     * 保存
     *
     * @param order
     * @return void
     * @author LSD
     * @date 2019/7/4
     */
    void add(Order order);

    /**
     * 删除
     *
     * @param order
     * @return void
     * @author LSD
     * @date 2019/7/4
     */
    void delete(Order order);
}
