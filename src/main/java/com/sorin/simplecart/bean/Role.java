package com.sorin.simplecart.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色
 *
 * @author LSD
 * @date 2019/06/20
 **/
@Entity
@Table(name = "role_")
public class Role {

    @Id
    @Column(length = 32, nullable = false)
    private String id;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(length = 32)
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
