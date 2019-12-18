# learn-spring-cloud-function

This is sample of "Spring Cloud Function" for my learning.  

## Build 

```bash
$ chmod +x gradlew
$ ./gradle build
```

## Run

```bash
$ chmod +x gradlew
$ ./gradle bootRun
```

## Try Api

- supplier api

```bash
$ curl -v localhost:8080/supplier
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /supplier HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
> 
< HTTP/1.1 200 
< user-agent: curl/7.64.1
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Mon, 09 Dec 2019 16:29:24 GMT
< 
* Connection #0 to host localhost left intact
{"name":"hogehoge","note":"notenote"}* Closing connection 0
```

Supplier source is `com.zomu.t.learnspringcloudfunction.function.simple.SimpleSupplier`  

- function api
```bash
$ curl -v localhost:8080/function -d 'hello'

```
- consume api
```bash
$ curl -v localhost:8080/supplier

```


# 調査事項

## 試しにSpringFoxを利用してSwagger-UIで覗いてみた

`FunctionalInterface` で実装した関数がAPI化されるため、  
この仕様がSpringFox（つまり、Swagger-UI）で表示することができたら  
仕様の展開がとても楽であるし、クライアントサイドもSwaggerを用いて実装できるので良いと考えて試してみた。

build.gradle
```groovy
dependencies {

    // omitted

	implementation "io.springfox:springfox-swagger2:2.9.2"
	implementation "io.springfox:springfox-swagger-ui:2.9.2"

}
```

起動をして、 http://localhost:8080/swagger-ui.html にアクセスするとWEBページが表示される。  
結果としては残念ながらSwagger-UIで定義した関数のAPIは確認できなかった。  
これはつまり、SpringMVCのControllerとして扱われていないということになる。

## Controller

Swagger-UIではAPI仕様が表示できなかったので、Controllerがどうなっているのかを調べた。  
`spring-cloud-function-web` を使用した場合、Functionを取り込んでControllerを自動的に作り出しているわけではなさそう。  
実態の `Ｃｏｎｔｒｏｌｌｅｒ` は `org.springframework.cloud.function.web.mvc.FunctionController` であるようだ。  

もしかしたら `SpringDataRest` とかもこういう作りになっているのかもしれない。


## APIを公開方法

### Implements and Publish Function

#### @ComponentScan

```java
@Component("hogecon")
public class HogeConsumer implements Consumer<String> {

    private static final Logger logger = LoggerFactory.getLogger(HogeConsumer.class);

    @Override
    public void accept(String s) {
        logger.info("accept. str -> {}", s);
    }
}
```

SpringのComponentScanの機能を利用して、API公開したい関数を登録する方法。  
`@Component("{name}")` で指定した `{name}` がURLになる。  
つまり、 http://localhost:8080/{name} ということになる。

#### @Configuration + @Bean

```java
public class HogeConsumer implements Consumer<String> {

    private static final Logger logger = LoggerFactory.getLogger(HogeConsumer.class);

    @Override
    public void accept(String s) {
        logger.info("accept. str -> {}", s);
    }
}

@Configuration
public class SampleConfiguration {

    @Bean("consumer")
    public Consumer<String> consumer() {
        return new HogeConsumer();
    }

}
```

SpringのConfigurationクラスにてBeanを定義して、API公開したい関数を登録する方法。  
`@Bean("｛name}")` で指定した `{name}` がURLになる。  
つまり、 http://localhost:8080/{name} ということになる。

### Method

メソッドは指定できないように思える。  
`org.springframework.cloud.function.web.mvc.FunctionController` を確認する限り、`GET/POST` の `/**` を対象にしている。  
`FuncationalInterface` の種類とマッピングとしては、[公式ドキュメント](https://cloud.spring.io/spring-cloud-function/reference/html/spring-cloud-function.html#_standalone_web_applications) に記載がある。

### PathVariable

PathVariableにも対応していないように思える。  
自分で独自に取り出すことは可能なのだろうが、SpringMVCのように簡単に取得できるようなものではない。

### BeanValidation

依存関係としてBeanValidation系のライブラリは通ってはいるものの、関数の入力BeanにValidationアノテーションを記載しても、  
その内容でBeanValidationを実施してくれるようなことはなかった。
