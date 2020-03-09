package com.sorin.simplecart.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LSD
 * @date 2020/03/07
 **/
@Configuration
public class DirectRabbitConfig {

    @Bean
    Queue directQueue() {
        return new Queue("my-direct-queue", true);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("my-direct-exchanger");
    }

    @Bean
    Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("directRouting");
    }
}
