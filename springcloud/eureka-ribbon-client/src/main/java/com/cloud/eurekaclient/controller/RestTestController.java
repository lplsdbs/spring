package com.cloud.eurekaclient.controller;

import com.cloud.eurekaclient.config.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTestController {
    @Autowired
    RibbonService ribbonService;
    @GetMapping("/hi")
    //消费注册的服务
    public String test2(@RequestParam(required =false,defaultValue = "forezp")String name){
       // RestTemplate restTemplate=new RestTemplate();
        return  ribbonService.hi(name);
    }
}
