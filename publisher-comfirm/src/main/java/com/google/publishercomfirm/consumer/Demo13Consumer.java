package com.google.publishercomfirm.consumer;

import com.google.publishercomfirm.message.Demo13Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = Demo13Message.QUEUE)
public class Demo13Consumer {

    @RabbitHandler //声明处理消息的方法
    public void onMessage(Demo13Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
//        int i = 1/0;

    }
}
