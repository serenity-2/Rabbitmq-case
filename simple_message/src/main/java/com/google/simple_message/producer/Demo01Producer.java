package com.google.simple_message.producer;

import com.google.simple_message.message.Demo01Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Demo01Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend() {
        Demo01Message message = new Demo01Message();
        message.setMsg("find work");
        rabbitTemplate.convertAndSend(Demo01Message.EXCHANGE,Demo01Message.ROUTING_KEY,message);
    }

    public void syncDefaultSend() {
        Demo01Message demo01Message = new Demo01Message();
        demo01Message.setMsg("not job find");
        //这里用队列名代替路由名是因为默认交换器，隐式地绑定到每个队列，路由键等于队列名称
        rabbitTemplate.convertAndSend(Demo01Message.QUEUE,demo01Message);
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
