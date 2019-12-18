package com.zomu.t.learnspringcloudfunction.function.bean;

import java.util.function.Function;

import com.zomu.t.learnspringcloudfunction.function.bean.bean.SampleBean;

public class BeanFunction implements Function<String, SampleBean> {

    @Override
    public SampleBean apply(String name) {
        SampleBean bean = new SampleBean();
        bean.setName(name);
        bean.setNote("note");
        return bean;
    }

}
