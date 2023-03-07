package com.google.timimg_message.consumer;

import com.google.timimg_message.message.Demo08Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = Demo08Message.DELAY_QUEUE)
public class Demo08Consumer {

    @RabbitHandler
    public void onMessage(Demo08Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
