package com.google.publisher_confirm_async.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitProducerConfirmCallback implements RabbitTemplate.ConfirmCallback {

    //在构造方法中，把自己设置到 RabbitTemplate 中，作为 Confirm 的回调
    public RabbitProducerConfirmCallback(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("[confirm][Confirm 成功 correlationData: {}]", correlationData);
        } else {
            log.error("[confirm][Confirm 失败 correlationData: {} cause: {}]", correlationData, cause);
        }
    }
}
