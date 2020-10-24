package com.cloud.eurekaclient.config;

import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer feign(){
        return  new Retryer.Default(100,SECONDS.toMillis(1),5);
    }
}
