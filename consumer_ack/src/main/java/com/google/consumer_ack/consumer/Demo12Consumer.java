package com.google.consumer_ack.consumer;

import com.google.consumer_ack.message.Demo12Message;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RabbitListener(queues = Demo12Message.QUEUE)
public class Demo12Consumer {

    @RabbitHandler
    public void onMessage(Demo12Message message, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG)long deliveryTag) throws IOException {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        //这里只是为了做对比差异性，只对指定消息内容做收到提交
        if (message.getMsg().equals("2022年乱花了不少钱，2023开始要节制！")) {
            // ack 确认消息
            // 第二个参数 multiple ，用于批量确认消息，为了减少网络流量，手动确认可以被批处。
            // 1. 当 multiple 为 true 时，则可以一次性确认 deliveryTag 小于等于传入值的所有消息
            // 2. 当 multiple 为 false 时，则只确认当前 deliveryTag 对应的消息
            channel.basicAck(deliveryTag, false);
        }
    }
}
