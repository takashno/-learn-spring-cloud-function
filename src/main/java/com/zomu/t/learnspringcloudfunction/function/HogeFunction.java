package com.zomu.t.learnspringcloudfunction.function;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class HogeFunction implements Function<Mono<String>, Mono<String>> {

    @Override
    public Mono<String> apply(Mono<String> s) {
        return Mono.create(x -> x.success(s.block() + "-add-str"));
    }

}
