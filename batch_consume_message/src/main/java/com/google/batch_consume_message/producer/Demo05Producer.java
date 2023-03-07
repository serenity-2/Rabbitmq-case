package com.google.batch_consume_message.producer;

import com.google.batch_consume_message.message.Demo05Message;
import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo05Producer {
    @Autowired
    private Demo05Producer producer;

    @Autowired
    private BatchingRabbitTemplate batchingRabbitTemplate;

    public void syncSend(String msg) {
        // 创建 Demo05Message 消息
        Demo05Message message = new Demo05Message();
        message.setMsg(msg);
        // 同步发送消息
        batchingRabbitTemplate.convertAndSend(Demo05Message.EXCHANGE, Demo05Message.ROUTING_KEY, message);
    }
}
