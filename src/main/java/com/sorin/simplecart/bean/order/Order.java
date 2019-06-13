package com.sorin.simplecart.bean.order;

import javax.persistence.*;

/**
 * order
 *
 * @author LSD
 * @date 2019/06/12
 **/
@Entity
@Table(name = "order_")
@IdClass(OrderKey.class)
public class Order {

    @Id
    @Column(length = 32)
    private String id;

    @Id
    @Column(length = 32)
    private String userId;

    @Id
    @Column(length = 32)
    private String itemId;


    @Column(precision = 8, nullable = false)
    private int number;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
