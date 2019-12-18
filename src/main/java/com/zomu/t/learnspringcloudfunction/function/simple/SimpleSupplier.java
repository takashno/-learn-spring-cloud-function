package com.zomu.t.learnspringcloudfunction.function.simple;

import java.util.function.Supplier;

public class SimpleSupplier implements Supplier<String> {

    @Override
    public String get() {
        return "SimpleSupplier#get called.";
    }

}
