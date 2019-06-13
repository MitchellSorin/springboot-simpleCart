package com.sorin.simplecart.dao;

import com.sorin.simplecart.bean.order.Order;
import com.sorin.simplecart.bean.order.OrderKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * orderDAO
 *
 * @author LSD
 * @date 2019/06/12
 **/
public interface OrderDAO extends JpaRepository<Order, OrderKey> {
}
