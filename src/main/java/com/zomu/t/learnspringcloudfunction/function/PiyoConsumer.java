package com.zomu.t.learnspringcloudfunction.function;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

import com.zomu.t.learnspringcloudfunction.bean.Hoge;

public class PiyoConsumer implements Consumer<Message<Hoge>> {
    private static final Logger logger = LoggerFactory.getLogger(PiyoConsumer.class);

    @Override public void accept(Message<Hoge> s) {

        s.getHeaders().entrySet().stream().forEach(x -> {
            logger.info(x.getKey() + " : " + x.getValue());
        });

        logger.info("payload -> {}", s.getPayload());
    }
}
