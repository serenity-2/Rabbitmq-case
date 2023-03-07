package com.google.consumer_ack.producer;

import com.google.consumer_ack.message.Demo12Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Demo12Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(String msg) {
        // 创建 Demo01Message 消息
        Demo12Message message = new Demo12Message();
        message.setMsg(msg);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo12Message.EXCHANGE, Demo12Message.ROUTING_KEY, message);
    }

    public void syncSendDefault(String msg) {
        // 创建 Demo01Message 消息
        Demo12Message message = new Demo12Message();
        message.setMsg(msg);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo12Message.QUEUE, message);
    }

    @Async
    public ListenableFuture<Void> asyncSend(String msg) {
        try {
            // 发送消息
            this.syncSend(msg);
            // 返回成功的 Future
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }
    }

}
