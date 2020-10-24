package com.cloud.eurekaserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
 import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
//@EnableHystrix
//@EnableHystrixDashboard
public class userServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(userServiceApplication.class, args);
    }



}
