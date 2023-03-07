package com.google.timimg_message.config;

import com.google.timimg_message.message.Demo08Message;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static class DirectExchangeDemoConfiguration {
        @Bean
        public Queue demo08Queue() {
            return QueueBuilder.durable(Demo08Message.QUEUE) //durable是否持久化
                               .exclusive()
                               .autoDelete()
                               .ttl(10 * 1000) //设置队列里默认过期时间为10秒
                               .deadLetterExchange(Demo08Message.EXCHANGE)
                               .deadLetterRoutingKey(Demo08Message.DELAY_ROUTING_KEY)
                               .build();
        }

        // 创建 Delay Queue
        @Bean
        public Queue demo08DelayQueue() {
            return new Queue(Demo08Message.DELAY_QUEUE, // Queue 名字
                    true, // durable: 是否持久化
                    false, // exclusive: 是否排它
                    false); // autoDelete: 是否自动删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo08Exchange() {
            return new DirectExchange(Demo08Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        // 创建 Binding
        // Exchange：Demo08Message.EXCHANGE
        // Routing key：Demo08Message.ROUTING_KEY
        // Queue：Demo08Message.QUEUE
        @Bean
        public Binding demo08Binding() {
            return BindingBuilder.bind(demo08Queue()).to(demo08Exchange()).with(Demo08Message.ROUTING_KEY);
        }

        // 创建 Delay Binding
        // Exchange：Demo08Message.EXCHANGE
        // Routing key：Demo08Message.DELAY_ROUTING_KEY
        // Queue：Demo08Message.DELAY_QUEUE
        @Bean
        public Binding demo08DelayBinding() {
            return BindingBuilder.bind(demo08DelayQueue()).to(demo08Exchange()).with(Demo08Message.DELAY_ROUTING_KEY);
        }
    }
}
