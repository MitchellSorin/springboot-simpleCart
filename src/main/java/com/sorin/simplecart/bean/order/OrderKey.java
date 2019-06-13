package com.sorin.simplecart.bean.order;

import java.io.Serializable;
import java.util.Objects;

/**
 * order主键
 *
 * @author LSD
 * @date 2019/06/12
 **/
public class OrderKey implements Serializable {

    private String id;

    private String userId;

    private String itemId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderKey orderKey = (OrderKey) o;
        return id.equals(orderKey.id) &&
                userId.equals(orderKey.userId) &&
                itemId.equals(orderKey.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, itemId);
    }

    @Override
    public String toString() {
        return "OrderKey{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", itemId='" + itemId + '\'' +
                '}';
    }
}
