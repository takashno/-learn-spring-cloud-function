package com.zomu.t.learnspringcloudfunction.scan2;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Scan2Consumer implements Consumer<String> {

    private static final Logger logger = LoggerFactory.getLogger(
            Scan2Consumer.class);

    @Override
    public void accept(String s) {
        logger.info("Scan2Consumer#accept. str -> {}", s);
    }

}
