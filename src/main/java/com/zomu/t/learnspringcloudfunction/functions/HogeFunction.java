package com.zomu.t.learnspringcloudfunction.functions;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class HogeFunction implements Function<String, String> {

    @Override
    public String apply(String s) {
        return s + "-add-str";
    }

}
