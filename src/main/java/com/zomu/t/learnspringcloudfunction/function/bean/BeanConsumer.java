package com.zomu.t.learnspringcloudfunction.function.bean;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zomu.t.learnspringcloudfunction.function.bean.bean.SampleBean;

public class BeanConsumer implements Consumer<SampleBean> {

    private static final Logger logger = LoggerFactory.getLogger(
            BeanConsumer.class);

    @Override
    public void accept(SampleBean bean) {
        logger.info("BeanConsumer#accept. bean -> {}", bean.toString());
    }

}
