package com.zomu.t.learnspringcloudfunction.function.bean;

import java.util.function.Supplier;

import com.zomu.t.learnspringcloudfunction.function.bean.bean.SampleBean;

public class BeanSupplier implements Supplier<SampleBean> {

    @Override
    public SampleBean get() {
        SampleBean bean = new SampleBean();
        bean.setName("name");
        bean.setNote("note");
        return bean;
    }

}
