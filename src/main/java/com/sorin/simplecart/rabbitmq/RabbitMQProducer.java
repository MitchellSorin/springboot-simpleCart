package com.sorin.simplecart.rabbitmq;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LSD
 * @date 2020/03/07
 **/
@RestController
@RequestMapping("/rabbitMQ")
@Api(tags = "rabbitMQ")
public class RabbitMQProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("sendToDirectQueue")
    @ApiOperation("sendToDirectQueue")
    public void sendToDirectQueue(String message) {
        rabbitTemplate.convertAndSend("my-direct-exchanger", "directRouting", message);
    }

    @GetMapping("sendToTopicQueue1")
    @ApiOperation("sendToTopicQueue1")
    public void sendToTopicQueue1(String message) {
        rabbitTemplate.convertAndSend("my-topic-exchanger", "topicRouting", message);
    }

    @GetMapping("sendToTopicQueue2")
    @ApiOperation("sendToTopicQueue2")
    public void sendToTopicQueue2(String message) {
        rabbitTemplate.convertAndSend("my-topic-exchanger", "topicRouting.2", message);
    }

    @GetMapping("sendToFanoutQueue")
    @ApiOperation("sendToFanoutQueue")
    public void sendToFanoutQueue(String message) {
        rabbitTemplate.convertAndSend("my-fanout-exchanger", null, message);
    }
}
