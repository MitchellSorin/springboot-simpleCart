package com.sorin.simplecart.controller;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import com.sorin.simplecart.bean.Item;
import com.sorin.simplecart.service.api.ItemService;
import com.sorin.simplecart.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ItemService itemService;

    @GetMapping
    @ApiOperation(value = "查询全部")
    public Object select() {
        return new BaseResult<>(itemService.selectAll());
    }

    @GetMapping("selectByName")
    @ApiOperation(value = "通过名称查询")
    public Object selectByName(
            @RequestParam(value = "name") String name
    ) {
        return new BaseResult<>(itemService.selectByName(name));
    }

    @PostMapping
    @ApiOperation(value = "新增或修改")
    public Object add(
            @RequestParam(required = false, value = "id") String id,
            @RequestParam(value = "price") BigDecimal price,
            @RequestParam(value = "name") String name
    ) {
        Item item = new Item();
        item.setPrice(price);
        item.setName(name);
        if (StringUtils.isBlank(id)) {
            id = StringUtils.random(32);
        }
        item.setId(id);
        itemService.add(item);
        return new BaseResult<>(BaseResultConstant.SUCCESS);
    }

    @DeleteMapping
    @ApiOperation(value = "删除")
    public Object delete(
            @RequestParam(value = "id") String id
    ) {
        Item item = new Item();
        item.setId(id);
        itemService.delete(item);
        return new BaseResult(BaseResultConstant.SUCCESS);
    }

}
