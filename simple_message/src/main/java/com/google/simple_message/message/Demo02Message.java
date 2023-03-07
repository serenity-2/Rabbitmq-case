package com.google.simple_message.message;

import lombok.Data;

import java.io.Serializable;

@Data
public class Demo02Message implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_02";

    public static final String EXCHANGE = "EXCHANGE_DEMO_02";

    public static final String ROUTING_KEY = "#.chatGTP.*";

    private String message;

}
