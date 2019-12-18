package com.zomu.t.learnspringcloudfunction;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.zomu.t.learnspringcloudfunction.function.http.HttpConsumer;
import com.zomu.t.learnspringcloudfunction.function.http.HttpValidationConsumer;
import com.zomu.t.learnspringcloudfunction.function.http.bean.BodyBean;
import com.zomu.t.learnspringcloudfunction.function.http.bean.BodyValidationBean;
import com.zomu.t.learnspringcloudfunction.function.simple.SimpleConsumer;
import com.zomu.t.learnspringcloudfunction.function.simple.SimpleFunction;
import com.zomu.t.learnspringcloudfunction.function.simple.SimpleSupplier;

@Configuration
public class LearnSpringCloudFunctionConfiguration {

    /* ------ simple ------ */

    @Bean("simple/supplier")
    public Supplier<String> simpleSupplier() {
        return new SimpleSupplier();
    }

    @Bean("simple/function")
    public Function<String, String> simpleFunction() {
        return new SimpleFunction();
    }

    @Bean("simple/consumer")
    public Consumer<String> simpleConsumer() {
        return new SimpleConsumer();
    }

    /* ------ bean ------ */

    @Bean("bean/supplier")
    public Supplier<String> beanSupplier() {
        return new SimpleSupplier();
    }

    @Bean("bean/function")
    public Function<String, String> beanFunction() {
        return new SimpleFunction();
    }

    @Bean("bean/consumer")
    public Consumer<String> beanConsumer() {
        return new SimpleConsumer();
    }

    /* ------ http ------ */

    @Bean("http/consumer")
    public Consumer<Message<BodyBean>> httpConsumer() {
        return new HttpConsumer();
    }

    @Bean("http/validation/consumer")
    public Consumer<Message<BodyValidationBean>> httpValidationConsumer() {
        return new HttpValidationConsumer();
    }
}