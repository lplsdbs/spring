package com.example.rabbitmq_boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RabbitmqBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqBootApplication.class, args);
    }





}
