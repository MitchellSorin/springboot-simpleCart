package com.sorin.simplecart.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * item
 *
 * @author LSD
 * @date 2019/06/12
 **/
@Entity
@Table(name = "item_")
public class Item {
    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
