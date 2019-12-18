package com.zomu.t.learnspringcloudfunction.function;

import com.zomu.t.learnspringcloudfunction.bean.Hoge;

import java.util.function.Supplier;

import org.springframework.stereotype.Component;

@Component("hogesup")
public class HogeSupplier implements Supplier<Hoge> {
    @Override
    public Hoge get() {
        var hoge = new Hoge();
        hoge.setName("hogehoge");
        hoge.setNote("notenote");
        return hoge;
    }
}
