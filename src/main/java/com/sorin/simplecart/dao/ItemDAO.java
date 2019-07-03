package com.sorin.simplecart.dao;

import com.sorin.simplecart.bean.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * itemDAO
 *
 * @author LSD
 * @date 2019/06/12
 **/
public interface ItemDAO extends JpaRepository<Item, String> {

    List<Item> findByNameContains(String name);
}
