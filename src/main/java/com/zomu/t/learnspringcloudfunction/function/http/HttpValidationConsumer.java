package com.zomu.t.learnspringcloudfunction.function.http;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

import com.zomu.t.learnspringcloudfunction.function.http.bean.BodyValidationBean;

public class HttpValidationConsumer implements Consumer<Message<BodyValidationBean>> {

    private static final Logger logger = LoggerFactory.getLogger(HttpValidationConsumer.class);

    @Override
    public void accept(Message<BodyValidationBean> s) {

        s.getHeaders().entrySet().stream().forEach(x -> {
            logger.info(x.getKey() + " : " + x.getValue());
        });

        logger.info("payload -> {}", s.getPayload());
    }

}
