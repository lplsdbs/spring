package com.cloud.eurekaserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTestController {
    @GetMapping("/testRest")
    public String test(){
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForObject("https://www.baidu.com",String.class);
    }

}
