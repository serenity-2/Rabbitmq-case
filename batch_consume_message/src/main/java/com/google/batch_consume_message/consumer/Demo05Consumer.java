package com.google.batch_consume_message.consumer;

import com.google.batch_consume_message.message.Demo05Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RabbitListener(queues = Demo05Message.QUEUE,
        containerFactory = "consumerBatchContainerFactory")
public class Demo05Consumer {

    @RabbitHandler
    public void onMessage(List<Demo05Message> messages) {
        log.info("[onMessage][线程编号:{} 消息数量：{} 消息内容:{}]", Thread.currentThread().getId(), messages.size(),messages);
    }
}
