package com.zomu.t.learnspringcloudfunction.scan1;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scan1Consumer implements Consumer<String> {

    private static final Logger logger = LoggerFactory.getLogger(
            Scan1Consumer.class);

    @Override
    public void accept(String s) {
        logger.info("Scan1Consumer#accept. str -> {}", s);
    }

}
