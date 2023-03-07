package com.google.publisher_confirm_async;

import com.google.publisher_confirm_async.producer.Demo14Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest
public class Demo14ProducerTest {

    @Autowired
    private Demo14Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend("暂不想去发展副业，还是学好当下的Java吧");
        log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testSyncSendNoQueue() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSendReturn("年轻人不能迷失了方向");
        log.info("[testSyncSendNoQueue][发送编号：[{}] 发送成功]", id);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }
}
