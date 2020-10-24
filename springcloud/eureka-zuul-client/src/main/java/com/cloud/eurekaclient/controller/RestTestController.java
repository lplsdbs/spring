package com.cloud.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTestController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/testRest")
    public String test(){
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject("https://www.baidu.com",String.class);
    }
    @GetMapping("/hi")
    public String test2(String name){
       // RestTemplate restTemplate=new RestTemplate();
        return"eureka-client port:"+port+" name "+name;
    }
}
