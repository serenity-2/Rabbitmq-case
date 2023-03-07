package com.google.publishercomfirm.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo13Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_13";

    public static final String EXCHANGE = "EXCHANGE_DEMO_13";

    public static final String ROUTING_KEY = "ROUTING_KEY_13";

    /**
     * 消息内容
     */
    private String msg;
}
