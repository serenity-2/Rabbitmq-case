package com.google.timimg_message.producer;

import com.google.timimg_message.message.Demo08Message;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo08Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer delay) {
        Demo08Message message = new Demo08Message();
        message.setMsg("java开发再做4年转人工智能");
        //同步发送消息
        rabbitTemplate.convertAndSend(Demo08Message.EXCHANGE, Demo08Message.ROUTING_KEY, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置消息的ttl过期时间
                if (delay != null && delay > 0) {
                    message.getMessageProperties().setExpiration(delay.toString());
                }
                return message;
            }
        });

    }
}
