package com.sorin.simplecart.dao;

import com.sorin.simplecart.bean.order.Order;
import com.sorin.simplecart.bean.order.OrderKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * orderDAO
 *
 * @author LSD
 * @date 2019/06/12
 **/
public interface OrderDAO extends JpaRepository<Order, OrderKey> {

    List<Order> findByUserIdEquals(String userId);

    @Query(nativeQuery = true,
            value = "select o.* from order_ o left join item_ i on o.item_id = i.id where o.user_id = ? and i.name like '%'?'%'")
    List<Order> selectByUserIdEqualsAndItemNameLike(String userId, String itemName);

    Order findByUserIdEqualsAndItemIdEquals(String userId, String itemId);

}
