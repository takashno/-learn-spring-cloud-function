package com.zomu.t.learnspringcloudfunction.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class HogeConsumer implements Consumer<String> {
    private static final Logger logger = LoggerFactory.getLogger(HogeConsumer.class);
    @Override
    public void accept(String s) {
        logger.info("accept. str -> {}", s);
    }
}
