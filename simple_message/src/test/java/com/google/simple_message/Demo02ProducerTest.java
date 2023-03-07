package com.google.simple_message;

import com.google.simple_message.producer.Demo02Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest
public class Demo02ProducerTest {
    @Autowired
    private Demo02Producer producer;

    @Test
    public void testSyncSendSuccess() throws InterruptedException {
        producer.syncSend( "Oh.chatGTP.真可怕");
        log.info("[testSyncSend]发送成功]");
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testSyncSendFailure() throws InterruptedException {
        producer.syncSend("未来.类chatGTP产品.会得到进一步提升");  //消息未匹配任何队列，发送失败
        log.info("[testSyncSendFailure] 发送成功]");

        // 阻塞等待，保证消费
        //CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。使用一个计数器进行实现。
        // 计数器初始值为线程的数量。当每一个线程完成自己任务后，计数器的值就会减一。当计数器的值为0时，
        // 表示所有的线程都已经完成一些任务，然后在CountDownLatch上等待的线程就可以恢复执行接下来的任务。
        new CountDownLatch(1).await();
    }
}
