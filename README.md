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

- Simple Supplier API

```bash
curl -v http://localhost:8080/simple/supplier
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /simple/supplier HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.55.1
> Accept: */*
>
< HTTP/1.1 200
< user-agent: curl/7.55.1
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 26
< Date: Wed, 18 Dec 2019 06:19:10 GMT
<
SimpleSupplier#get called.* Connection #0 to host localhost left intact
```

- Simple Function API
```bash
curl -X POST -v http://localhost:8080/simple/function -H "Content-Type: plain/text" -d str
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> POST /simple/function HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.55.1
> Accept: */*
> Content-Type: plain/text
> Content-Length: 3
>
* upload completely sent off: 3 out of 3 bytes
< HTTP/1.1 200
< user-agent: curl/7.55.1
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 11
< Date: Wed, 18 Dec 2019 06:23:53 GMT
<
str-add-str* Connection #0 to host localhost left intact
```
- Simple Consume API
```bash
curl -X POST -v http://localhost:8080/simple/consumer -H "Content-Type: plain/text" -d str
Note: Unnecessary use of -X or --request, POST is already inferred.
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> POST /simple/consumer HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.55.1
> Accept: */*
> Content-Type: plain/text
> Content-Length: 3
>
* upload completely sent off: 3 out of 3 bytes
< HTTP/1.1 202
< Content-Length: 0
< Date: Wed, 18 Dec 2019 06:24:42 GMT
<
* Connection #0 to host localhost left intact
```

# Leaning...

## Try SpringFox and Swagger-UI

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
実態の `Controller` は `org.springframework.cloud.function.web.mvc.FunctionController` であるようだ。  

もしかしたら `SpringDataRest` とかもこういう作りになっているのかもしれない。


## Publish API

### Implements and Publish Function

#### `@Component` + ComponentScan

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

#### `@Configuration` + `@Bean`

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

Springの `Configuration` クラスにてBeanを定義して、API公開したい関数を登録する方法。  
`@Bean("｛name}")` で指定した `{name}` がURLになる。  
つまり、 http://localhost:8080/{name} ということになる。

#### Function Component Scan

`Spring Cloud Function` では、「`@Component` + `@ComponentScan`」 や 「`@Configuration` + `@Bean`」による関数の登録以外に  
`Function Component Scan` という独自の機能を持つ。  
これは、`FunctionalInterface` のうち `Supplier`、`Consumer`、`Function` について自動でAPI公開する関数として認識させるものになる。

この機能はデフォルトで 「`true`」となっており、「`functions`」というパッケージがあれば対象となるように組まれている。  
`src/main/java/functions` という意味である。

この機能の設定は、`application.properties` にて行える。

```properties
spring.cloud.function.scan.enabled=true
spring.cloud.function.scan.packages=com.zomu.t.learnspringcloudfunction.scan1
```

複数パッケージは、カンマ区切りで指定可能となる。

```properties
spring.cloud.function.scan.enabled=true
spring.cloud.function.scan.packages=com.zomu.t.learnspringcloudfunction.scan1,com.zomu.t.learnspringcloudfunction.scan2
```
  
URLとしては、クラス名を `LowerCamel` にした文字が使用される。  
`Scan1Consumer` というクラスの場合、 http://localhost:8080/scan1Consumer というURLになる。

### Method

メソッドは指定できないように思える。  
`org.springframework.cloud.function.web.mvc.FunctionController` を確認する限り、`GET/POST` の `/**` を対象にしている。  
`FuncationalInterface` の種類とマッピングとしては、[公式ドキュメント](https://cloud.spring.io/spring-cloud-function/reference/html/spring-cloud-function.html#_standalone_web_applications) に記載がある。

### Content-Type / Accept

調査中

### PathVariable

PathVariableにも対応していないように思える。  
自分で独自に取り出すことは可能なのだろうが、SpringMVCのように簡単に取得できるようなものではない。

### BeanValidation

依存関係としてBeanValidation系のライブラリは通ってはいるものの、関数の入力BeanにValidationアノテーションを記載しても、  
その内容でBeanValidationを実施してくれるようなことはなかった。
