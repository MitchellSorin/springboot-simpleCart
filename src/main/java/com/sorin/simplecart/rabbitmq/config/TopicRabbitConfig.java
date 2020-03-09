package com.sorin.simplecart.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LSD
 * @date 2020/03/07
 **/
@Configuration
public class TopicRabbitConfig {

    @Bean
    Queue topicQueue() {
        return new Queue("my-topic-queue", true);
    }

    @Bean
    Queue topicQueue3() {
        return new Queue("my-topic-queue3", true);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("my-topic-exchanger");
    }

    @Bean
    Binding topicBinding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topicRouting.#");
    }

    @Bean
    Binding topicBinding3() {
        return BindingBuilder.bind(topicQueue3()).to(topicExchange()).with("topicRouting.#");
    }
}
