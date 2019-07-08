package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.Item;
import com.sorin.simplecart.bean.order.Order;
import com.sorin.simplecart.service.api.ItemService;
import com.sorin.simplecart.service.api.OrderService;
import com.sorin.simplecart.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询全部订单")
    public Object select(
    ) {
        try {
            Subject subject = SecurityUtils.getSubject();
            String userId = subject.getPrincipal().toString();
            return new BaseResult(BaseResultConstant.SUCCESS, orderService.selectByUserId(userId));
        } catch (Exception e) {
            logger.error("ItemController.select--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ApiOperation(value = "订单搜索")
    public Object search(
            @RequestParam(value = "itemName") String itemName
    ) {
        try {
            Subject subject = SecurityUtils.getSubject();
            String userId = subject.getPrincipal().toString();
            return new BaseResult(BaseResultConstant.SUCCESS, orderService.selectByUserIdAndItemName(userId, itemName));
        } catch (Exception e) {
            logger.error("ItemController.search--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增")
    public Object add(
            @RequestParam(value = "itemId") String itemId,
            @RequestParam(value = "num") int num
    ) {
        try {
            Subject subject = SecurityUtils.getSubject();
            String userId = subject.getPrincipal().toString();
            Item item = itemService.selectById(itemId);
            if (null == item) {
                return new BaseResult(BaseResultConstant.FAILED, "没有此商品");
            }

            Order order = new Order();
            order.setItemId(itemId);
            order.setUserId(userId);
            order.setNumber(num);
            order.setId(StringUtils.random(32));
            orderService.add(order);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("ItemController.add--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除")
    public Object delete(
            @RequestParam(value = "id") String id
    ) {
        try {
            Order order = new Order();
            order.setId(id);
            orderService.delete(order);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("ItemController.delete--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }
}
