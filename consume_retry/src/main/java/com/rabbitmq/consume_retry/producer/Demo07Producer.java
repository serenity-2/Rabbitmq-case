package com.rabbitmq.consume_retry.producer;

import com.rabbitmq.consume_retry.message.Demo07Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Demo07Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend() {
        Demo07Message message = new Demo07Message();
        message.setMsg("周末吃了蟹公煲");
        rabbitTemplate.convertAndSend(Demo07Message.EXCHANGE,Demo07Message.ROUTING_KEY,message);
    }

    public void syncDefaultSend() {
        Demo07Message demo07Message = new Demo07Message();
        demo07Message.setMsg("乐观 自信 自律");
        //这里用队列名代替路由名是因为默认交换器，隐式地绑定到每个队列，路由键等于队列名称
        rabbitTemplate.convertAndSend(Demo07Message.QUEUE,demo07Message);
    }

    /**
     * 发送异步消息
     * @return
     */
    @Async
    public ListenableFuture<Void> asyncSend() {
        try {
            this.syncSend();
            //返回成功的Future
            return AsyncResult.forValue(null);
        } catch (Exception e) {
            //返回失败的Future
            return AsyncResult.forExecutionException(e);
        }
    }
}
