package com.google.timimg_message;

import com.google.timimg_message.producer.Demo08Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest
public class Demo08ProducerTest {
    @Autowired
    private Demo08Producer producer;


    @Test
    public void testSyncSend01() throws InterruptedException {
        // 不设置消息的过期时间，使用队列默认的消息过期时间
        this.testSyncSendDelay(null);
    }

    @Test
    public void testSyncSend02() throws InterruptedException {
        // 设置发送消息的过期时间为 5000 毫秒
        this.testSyncSendDelay(5000);
    }

    private void testSyncSendDelay(Integer delay) throws InterruptedException {
        producer.syncSend(delay);
        log.info("[testSyncSendDelay][发送编号：[{}] 发送成功]", System.currentTimeMillis());

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
