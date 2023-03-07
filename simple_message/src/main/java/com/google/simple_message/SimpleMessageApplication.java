package com.google.simple_message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SimpleMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleMessageApplication.class, args);
    }

}
