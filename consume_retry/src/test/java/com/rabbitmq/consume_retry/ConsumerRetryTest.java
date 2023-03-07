package com.rabbitmq.consume_retry;

import com.rabbitmq.consume_retry.producer.Demo07Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest
public class ConsumerRetryTest {

    @Autowired
    private Demo07Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            // 同步发送消息
            int id = (int) (System.currentTimeMillis() / 1000);
            producer.syncSend();
            // 故意每条消息之间，隔离 10 秒
            log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            Thread.sleep(10 * 1000L);
        }

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
