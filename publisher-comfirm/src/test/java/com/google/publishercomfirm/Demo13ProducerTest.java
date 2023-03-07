package com.google.publishercomfirm;

import com.google.publishercomfirm.producer.Demo13Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest
public class Demo13ProducerTest {
    @Autowired
    private Demo13Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        producer.syncSend("falling you");
        log.info("[testSyncSend][发送编号：[{}] 发送成功]", System.currentTimeMillis());
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
