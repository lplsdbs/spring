package com.cloud.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//特别注意版本信息,目前用1.5.3做的有问题不能/bus/refresh之后配置未刷新过来
//spring-boot-starter-parent： 2.0.3.RELEASE
//spring-cloud.version：Finchley.RELEASE
@RestController
@RefreshScope
public class RestTestController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/testRest")
    public String test(){
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject("https://www.baidu.com",String.class);
    }
    @Value("${foo}")
    String foo;
    @GetMapping("/hi")
    public String hi(){
        return foo;
    }
}
