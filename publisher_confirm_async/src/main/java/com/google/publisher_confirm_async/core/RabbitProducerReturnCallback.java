package com.google.publisher_confirm_async.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitProducerReturnCallback implements RabbitTemplate.ReturnsCallback {

    public RabbitProducerReturnCallback(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setReturnsCallback(this);
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.error("消息:{}发送失败：未匹配到任何队列",returnedMessage.getMessage());

    }
}
