package com.sorin.simplecart.service.impl;

import com.sorin.simplecart.bean.Item;
import com.sorin.simplecart.dao.ItemDAO;
import com.sorin.simplecart.service.api.ItemService;
import com.sorin.simplecart.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (RedisUtils.hasKey("item:all")) {
            return (List<Item>) RedisUtils.get("item:all");
        }
        List<Item> all = itemDAO.findAll();
        RedisUtils.set("item:all", all,1800);
        return all;
    }

    @Override
    public List<Item> selectByName(String name) {
        List<Item> items = this.selectAll();
        List<Item> itemsByName = new ArrayList<>();
        if (null != items && items.size() > 0) {
            for (Item item : items) {
                if (item.getName().contains(name)) {
                    itemsByName.add(item);
                }
            }
        }
        return itemsByName;
    }

    @Override
    public Item selectById(String id) {
        if (RedisUtils.hasKey("item:item_id:" + id)) {
            return (Item) RedisUtils.get("item:item_id:" + id);
        }
        Optional<Item> op = itemDAO.findById(id);
        Item item = op.orElse(null);
        RedisUtils.set("item:item_id:" + id, item,1800);
        return item;
    }

    @Override
    public void add(Item item) {
        RedisUtils.del("item:all");
        itemDAO.saveAndFlush(item);
    }

    @Override
    public void delete(Item item) {
        RedisUtils.del("item:all", "item:item_id:" + item.getId());
        itemDAO.delete(item);
    }
}
