package com.zomu.t.learnspringcloudfunction.function.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class SimpleConsumer implements Consumer<String> {

    private static final Logger logger = LoggerFactory.getLogger(SimpleConsumer.class);

    @Override
    public void accept(String s) {
        logger.info("SimpleConsumer#accept. str -> {}", s);
    }

}
