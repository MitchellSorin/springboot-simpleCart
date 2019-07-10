package com.sorin.simplecart.websocket;

import com.sorin.simplecart.baseresult.BaseResult;
import com.sorin.simplecart.baseresult.BaseResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 测试websocket
 *
 * @author LSD
 * @date 2019/07/09
 **/
@RestController
@Api(tags = "测试WebSocket", description = "测试地址:/wsTest")
public class WsTestController {

    @RequestMapping(value = "/ws/broadCastTime", method = RequestMethod.GET)
    @ApiOperation(value = "广播时间")
    public Object broadCastTime() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
            WsServerManager.broadCast("当前时间是：" + simpleDateFormat.format(new Date()));
            return new BaseResult(BaseResultConstant.SUCCESS, "已广播");
        } catch (Exception e) {
            return new BaseResult(BaseResultConstant.FAILED, null);
        }
    }
}
