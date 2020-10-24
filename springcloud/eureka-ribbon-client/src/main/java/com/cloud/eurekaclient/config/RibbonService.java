package com.cloud.eurekaclient.config;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;
    //熔断机制如果请求的失败次数大于设定的法制打开熔断器执行fallback机制，请求不处于阻塞状态，防止系统的雪崩效应
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String str){
        return restTemplate.getForObject("http://eureka-client/hi?name="+str,String.class);
    }
    public String hiError(String name){
        return "hi,"+name+".sorry,error!";
    }
}
