package com.sorin.simplecart.service.api;

import com.sorin.simplecart.bean.Item;

import java.util.List;

/**
 * 商品service
 *
 * @author LSD
 * @date 2019/07/03
 **/
public interface ItemService {

    /**
     * 查询全部
     *
     * @return java.util.List<com.sorin.simplecart.bean.Item>
     * @author LSD
     * @date 2019/7/3
     */
    List<Item> selectAll();

    /**
     * 通过名称查询
     *
     * @param name
     * @return java.util.List<com.sorin.simplecart.bean.Item>
     * @author LSD
     * @date 2019/7/3
     */
    List<Item> selectByName(String name);

    /**
     * 通过id查询
     *
     * @param id
     * @return com.sorin.simplecart.bean.Item
     * @author LSD
     * @date 2019/7/3
     */
    Item selectById(String id);

    /**
     * 保存
     *
     * @param item
     * @return void
     * @author LSD
     * @date 2019/7/3
     */
    void add(Item item);

    /**
     * 删除
     *
     * @param item
     * @return void
     * @author LSD
     * @date 2019/7/3
     */
    void delete(Item item);
}
