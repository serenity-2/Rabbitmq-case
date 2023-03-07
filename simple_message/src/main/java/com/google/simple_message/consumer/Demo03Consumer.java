package com.google.simple_message.consumer;

import com.google.simple_message.message.Demo02Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = Demo02Message.QUEUE)
public class Demo03Consumer {
    @RabbitHandler
    public void onMessage(Demo02Message demo02Message) {
        log.info("[onMessage][线程编号--------------------->:{} 消息内容：{}]", Thread.currentThread().getId(), demo02Message);
    }
}
