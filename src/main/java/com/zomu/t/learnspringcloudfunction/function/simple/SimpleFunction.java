package com.zomu.t.learnspringcloudfunction.function.simple;

import java.util.function.Function;

public class SimpleFunction implements Function<String, String> {

    @Override
    public String apply(String s) {
        return s + "-add-str";
    }

}
