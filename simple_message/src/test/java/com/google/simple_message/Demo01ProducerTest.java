package com.google.simple_message;

import com.google.simple_message.message.Demo01Message;
import com.google.simple_message.producer.Demo01Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class Demo01ProducerTest {
    @Autowired
    private Demo01Producer demo01Producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        demo01Producer.syncSend();
        System.out.println("syncSend success");
        //阻塞等待 保证消费
        new CountDownLatch(1).await(3, TimeUnit.SECONDS);
    }


    @Test
    public void testSyncDefaultSend() throws InterruptedException {
        demo01Producer.syncDefaultSend();
        System.out.println("syncDefaultSend success");
        //阻塞等待 保证消费
        new CountDownLatch(1).await();
    }


    @Test
    public void testAsyncSend() {
       demo01Producer.asyncSend().addCallback(new ListenableFutureCallback<Void>() {
           @Override
           public void onFailure(Throwable throwable) {
               System.out.println("异步消息发送失败：找不到工作");
           }

           @Override
           public void onSuccess(Void unused) {
               System.out.println("异步消息发送成功：找到工作啦");
           }
       });
        System.out.println("asyncSend send over");
    }

}
