package com.google.consumer_ack;

import com.google.consumer_ack.producer.Demo12Producer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest
public class Demo12ProducerTest {
    @Autowired
    private Demo12Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        for (int id = 1; id <= 2; id++) {
            if (id == 1) {
                producer.syncSend("2022年乱花了不少钱，2023开始要节制！");
                log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            }else {
                producer.syncSend("2023开头难，但要有信心");
                log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            }
        }
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}
