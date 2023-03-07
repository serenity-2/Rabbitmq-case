package com.google.batch_send_message.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo05Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_05";

    public static final String EXCHANGE = "EXCHANGE_DEMO_05";

    public static final String ROUTING_KEY = "ROUTING_KEY_05";

    /**
     * 编号
     */
    private String msg;

}
