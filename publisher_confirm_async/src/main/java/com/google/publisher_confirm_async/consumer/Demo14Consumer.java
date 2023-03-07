package com.google.publisher_confirm_async.consumer;

import com.google.publisher_confirm_async.message.Demo14Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = Demo14Message.QUEUE)
public class Demo14Consumer {

    @RabbitHandler //声明处理消息的方法
    public void onMessage(Demo14Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);

    }
}
