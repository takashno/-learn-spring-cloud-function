package com.zomu.t.learnspringcloudfunction;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.zomu.t.learnspringcloudfunction.bean.Hoge;
import com.zomu.t.learnspringcloudfunction.function.FugaConsumer;
import com.zomu.t.learnspringcloudfunction.function.HogeConsumer;
import com.zomu.t.learnspringcloudfunction.function.HogeFunction;
import com.zomu.t.learnspringcloudfunction.function.HogeSupplier;
import com.zomu.t.learnspringcloudfunction.function.PiyoConsumer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class LearnSpringCloudFunctionConfiguration {

    @Bean
    public Function<Flux<String>, Flux<String>> uppercase() {
        return flux -> flux.map(value -> value.toUpperCase());
    }

    @Bean("supplier")
    public Supplier<Hoge> sup() {
        return new HogeSupplier();
    }

    @Bean("function")
    public Function<Mono<String>, Mono<String>> function() {
        return new HogeFunction();
    }

    @Bean("consumer")
    public Consumer<String> consumer() {
        return new HogeConsumer();
    }

    @Bean("fuga_con")
    public Consumer<Message<String>> fugaConsumer() {
        return new FugaConsumer();
    }

    @Bean("piyo_con/{aaa}")
    public Consumer<Message<Hoge>> piyoConsumer() {
        return new PiyoConsumer();
    }
}