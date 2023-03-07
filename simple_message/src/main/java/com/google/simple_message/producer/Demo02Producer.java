package com.google.simple_message.producer;

import com.google.simple_message.message.Demo02Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo02Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(String routingKey) {
        // 创建 Demo02Message 消息
        Demo02Message message = new Demo02Message();
        message.setMessage("chatGTP 人工智能领域的一次重大提升");
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo02Message.EXCHANGE, routingKey, message);
    }
}
