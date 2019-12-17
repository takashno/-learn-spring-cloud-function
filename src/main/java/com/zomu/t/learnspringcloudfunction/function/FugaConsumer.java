package com.zomu.t.learnspringcloudfunction.function;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

public class FugaConsumer implements Consumer<Message<String>> {
    private static final Logger logger = LoggerFactory.getLogger(FugaConsumer.class);

    @Override public void accept(Message<String> s) {

        s.getHeaders().entrySet().stream().forEach(x -> {
            logger.info(x.getKey() + " : " + x.getValue());
        });

        logger.info("payload -> {}", s.getPayload());
    }
}
