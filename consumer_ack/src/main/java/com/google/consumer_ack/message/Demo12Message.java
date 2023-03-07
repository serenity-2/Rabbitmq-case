package com.google.consumer_ack.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo12Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_12";

    public static final String EXCHANGE = "EXCHANGE_DEMO_12";

    public static final String ROUTING_KEY = "ROUTING_KEY_12";

    /**
     * 编号
     */
    private String msg;
}
