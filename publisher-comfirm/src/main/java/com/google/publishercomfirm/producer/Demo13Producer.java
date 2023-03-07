package com.google.publishercomfirm.producer;

import com.google.publishercomfirm.message.Demo13Message;
import com.rabbitmq.client.ConfirmCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Demo13Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(String msg) {
        Demo13Message message = new Demo13Message();
        message.setMsg(msg);
        rabbitTemplate.invoke(new RabbitOperations.OperationsCallback<Object>() {

            @Override
            public Object doInRabbit(RabbitOperations rabbitOperations) {
                //同步发送消息
                rabbitOperations.convertAndSend(Demo13Message.EXCHANGE, Demo13Message.ROUTING_KEY, message);
                log.info("[doInRabbit][发送消息完成]");
                //同步阻塞 等待确认 timeout 参数，如果传递 0 ，表示无限等待
                //从发送者发送消息到broker接受消息再到生产者confirm这个过程需要的时间，如果超过等待时间则会抛出超时异常
                rabbitOperations.waitForConfirms(1000);
                log.info("[doInRabbit][等待 Confirm 完成]");
                return null;
            }
        }, new ConfirmCallback() {
            @Override
            public void handle(long deliveryTag, boolean multiple) throws IOException {
                log.info("[handle][Confirm 成功]");
            }
        }, new ConfirmCallback() {
            @Override
            public void handle(long l, boolean b) throws IOException {
                log.info("[handle][Confirm 失败]");
            }
        });
    }
}
