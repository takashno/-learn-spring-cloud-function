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

Supplier source is `com.zomu.t.learnspringcloudfunction.function.HogeSupplier`  

- function api
```bash
$ curl -v localhost:8080/function -d 'hello'

```
- consume api
```bash
$ curl -v localhost:8080/supplier

``v