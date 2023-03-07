package com.google.simple_message.consumer;

import com.google.simple_message.message.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = Demo01Message.QUEUE) //声明消费的队列
public class Demo01Consumer {

    @RabbitHandler //声明处理消息的方法
    public void onMessage(Demo01Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);

    }
}
