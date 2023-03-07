package com.google.publisher_confirm_async.producer;

import com.google.publisher_confirm_async.message.Demo14Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Demo14Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(String msg) {
        // 创建 Demo14Message 消息
        Demo14Message message = new Demo14Message();
        message.setMsg(msg);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo14Message.EXCHANGE, Demo14Message.ROUTING_KEY, message);
    }

    /**
     * 发送一个匹配不到任何队列的消息，触发ReturnsCallBack
     * @param msg
     */
    public void syncSendReturn(String msg) {
        // 创建 Demo14Message 消息
        Demo14Message message = new Demo14Message();
        message.setMsg(msg);
        // 同步发送消息
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("1");
        rabbitTemplate.convertAndSend(Demo14Message.EXCHANGE, "error", message,correlationData);
    }
}
