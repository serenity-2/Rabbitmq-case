package com.rabbitmq.consume_retry.config;

import com.rabbitmq.consume_retry.message.Demo07Message;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static class DirectExchangeDemoConfiguration {
       //创建queue
        @Bean
        public Queue demo07Queue() {
            return QueueBuilder.durable(Demo07Message.QUEUE)
//                    .exclusive() //是否拍他
                    .autoDelete() // 是否自动删除
                    .deadLetterExchange(Demo07Message.EXCHANGE)
                    .deadLetterRoutingKey(Demo07Message.DEAD_ROUTING_KEY)
                    .build();
        }

        //创建dead queue
        @Bean
        public Queue demo07DeadQueue() {
            return new Queue(Demo07Message.DEAD_QUEUE,
                    true, // 是否持久化 RabbitMQ关闭后，没有持久化的Exchange将被清除
                    false, //是否拍他
                    false); //是否自动删除 如果没有与之绑定的Queue，直接删除
        }

        // 创建 Direct Exchange
        @Bean
        public DirectExchange demo07Exchange() {
            return new DirectExchange(Demo07Message.EXCHANGE,
                    true,  // durable: 是否持久化
                    false);  // exclusive: 是否排它
        }

        //创建banding
        @Bean
        public Binding demo07Banding() {
            return BindingBuilder.bind(demo07Queue()).to(demo07Exchange()).with(Demo07Message.ROUTING_KEY);
        }

        //创建死信队列的绑定，目的是对该队列的消息重新消费
        @Bean
        public Binding demo07DeadBinding() {
            return BindingBuilder.bind(demo07DeadQueue()).to(demo07Exchange()).with(Demo07Message.DEAD_ROUTING_KEY);
        }
    }
}
