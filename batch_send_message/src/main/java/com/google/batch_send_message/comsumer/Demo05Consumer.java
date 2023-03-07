package com.google.batch_send_message.comsumer;

import com.google.batch_send_message.message.Demo05Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component  //不要忘记加此注解
@RabbitListener(
        ueues = Demo05Message.QUEUE)
public class Demo05Consumer {

    @RabbitHandler
    public void onMessage(Demo05Message message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
