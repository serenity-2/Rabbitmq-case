package com.google.publisher_confirm_async.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo14Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_14";

    public static final String EXCHANGE = "EXCHANGE_DEMO_14";

    public static final String ROUTING_KEY = "ROUTING_KEY_14";

    /**
     * 消息内容
     */
    private String msg;
}
