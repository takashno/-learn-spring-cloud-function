package com.zomu.t.learnspringcloudfunction;

import com.zomu.t.learnspringcloudfunction.bean.Hoge;
import com.zomu.t.learnspringcloudfunction.functions.HogeSupplier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class LearnSpringCloudFunctionApplication {

	@Bean
	public Function<Flux<String>, Flux<String>> uppercase() {
		return flux -> flux.map(value -> value.toUpperCase());
	}

	@Bean("sup")
	public Supplier<Hoge> sup() {
		return new HogeSupplier();
	}

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringCloudFunctionApplication.class, args);
	}

}
