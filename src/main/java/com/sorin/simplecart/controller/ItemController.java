package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.Item;
import com.sorin.simplecart.service.api.ItemService;
import com.sorin.simplecart.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 商品管理
 *
 * @author LSD
 * @date 2019/07/03
 **/
@RestController
@RequestMapping("/cart/item")
@Api(tags = "商品", description = "CRUD")
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询全部")
    public Object select(
    ) {
        try {
            return new BaseResult(BaseResultConstant.SUCCESS, itemService.selectAll());
        } catch (Exception e) {
            logger.error("ItemController.select--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(value = "selectByName", method = RequestMethod.GET)
    @ApiOperation(value = "通过名称查询")
    public Object selectByName(
            @RequestParam(value = "name") String name
    ) {
        try {
            return new BaseResult(BaseResultConstant.SUCCESS, itemService.selectByName(name));
        } catch (Exception e) {
            logger.error("ItemController.selectByName--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "新增或修改")
    public Object add(
            @RequestParam(required = false, value = "id") String id,
            @RequestParam(value = "price") BigDecimal price,
            @RequestParam(value = "name") String name
    ) {
        try {
            Item item = new Item();
            item.setPrice(price);
            item.setName(name);
            if (StringUtils.isBlank(id)) {
                id = StringUtils.random(32);
            }
            item.setId(id);
            itemService.add(item);
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
            Item item = new Item();
            item.setId(id);
            itemService.delete(item);
            return new BaseResult(BaseResultConstant.SUCCESS, null);
        } catch (Exception e) {
            logger.error("ItemController.delete--error:", e);
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }

}
