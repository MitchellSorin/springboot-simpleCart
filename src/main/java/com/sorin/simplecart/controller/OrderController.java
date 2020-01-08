package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.Item;
import com.sorin.simplecart.bean.order.Order;
import com.sorin.simplecart.exception.CheckException;
import com.sorin.simplecart.service.api.ItemService;
import com.sorin.simplecart.service.api.OrderService;
import com.sorin.simplecart.shiro.SecurityUtil;
import com.sorin.simplecart.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 订单controller
 *
 * @author LSD
 * @date 2019/07/04
 **/
@RestController
@RequestMapping("/cart/order")
@Api(tags = "订单", description = "CRUD")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;

    @GetMapping
    @ApiOperation(value = "查询全部订单")
    public Object select() {
        return new BaseResult<>(orderService.selectByUserId(SecurityUtil.getUserId()));
    }

    @GetMapping("search")
    @ApiOperation(value = "订单搜索")
    public Object search(
            @RequestParam(value = "itemName") String itemName
    ) {
        return new BaseResult<>(orderService.selectByUserIdEqualsAndItemNameLike(SecurityUtil.getUserId(), itemName));
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public Object add(
            @RequestParam(value = "itemId") String itemId,
            @RequestParam(value = "num") int num
    ) {
        Item item = itemService.selectById(itemId);
        if (null == item) {
            throw new CheckException("没有此商品");
        }
        Order order = new Order();
        order.setItemId(itemId);
        order.setUserId(SecurityUtil.getUserId());
        order.setNumber(num);
        order.setId(StringUtils.random(32));
        orderService.add(order);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Object delete(
            @RequestParam(value = "id") String id
    ) {
        Order order = new Order();
        order.setId(id);
        orderService.delete(order);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }
}
