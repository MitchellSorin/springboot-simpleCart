package com.sorin.simplecart.activemq.current;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * 生产者
 *
 * @author LSD
 * @date 2020/03/06
 **/
@RestController
@RequestMapping("/activeMQ")
@Api(tags = "activeMQ")
public class Producer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;

    @PostMapping("queueSend")
    @ApiOperation("发送队列消息")
    public void queueSend(
            @RequestParam("message") String message
    ) {
        //默认使用queue
//        jmsMessagingTemplate.convertAndSend("my-queue", message);
        jmsMessagingTemplate.convertAndSend(queue, message);
    }

    @PostMapping("topicSend")
    @ApiOperation("发送主题消息")
    public void topicSend(
            @RequestParam("message") String message
    ) {
        jmsMessagingTemplate.convertAndSend(topic, message);
    }

    @JmsListener(destination = "out-queue", containerFactory = "jmsListenerContainerQueue")
    public void queueConsumerMessage(String msg) {
        System.out.println("out-queue收到回复：" + msg);
    }

    @JmsListener(destination = "out-topic", containerFactory = "jmsListenerContainerTopic")
    public void topicConsumerMessage(String msg) {
        System.out.println("out-topic收到回复：" + msg);
    }
}
