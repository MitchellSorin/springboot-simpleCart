package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.Item;
import com.sorin.simplecart.dao.ItemDAO;
import com.sorin.simplecart.service.api.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 商品serviceImpl
 *
 * @author LSD
 * @date 2019/07/03
 **/
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDAO itemDAO;

    @Override
    public List<Item> selectAll() {
        return itemDAO.findAll();
    }

    @Override
    public List<Item> selectByName(String name) {
        return itemDAO.findByNameContains(name);
    }

    @Override
    public Item selectById(String id) {
        Optional<Item> op = itemDAO.findById(id);
        if (op.isPresent()) {
            return op.get();
        } else {
            return null;
        }
    }

    @Override
    public void add(Item item) {
        itemDAO.saveAndFlush(item);
    }

    @Override
    public void delete(Item item) {
        itemDAO.delete(item);
    }
}
