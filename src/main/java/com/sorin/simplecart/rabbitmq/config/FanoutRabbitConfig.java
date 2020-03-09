package com.sorin.simplecart.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LSD
 * @date 2020/03/07
 **/
@Configuration
public class FanoutRabbitConfig {

    @Bean
    Queue fanoutQueue() {
        return new Queue("my-fanout-queue", true);
    }

    @Bean
    Queue fanoutQueue2() {
        return new Queue("my-fanout-queue2", true);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("my-fanout-exchanger");
    }

    @Bean
    Binding fanoutBinding() {
        return BindingBuilder.bind(fanoutQueue()).to(fanoutExchange());
    }

    @Bean
    Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
