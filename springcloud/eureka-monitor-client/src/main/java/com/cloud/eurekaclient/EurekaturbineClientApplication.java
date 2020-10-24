package com.cloud.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.ComponentScan;

//@EnableEurekaClient
@SpringBootApplication
@EnableHystrixDashboard
//这个注解必须有开启聚合监控
@EnableTurbine
public class EurekaturbineClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaturbineClientApplication.class, args);
    }

}
