package com.cloud.eurekaserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class RestTestController {
    @GetMapping("/current")
    public Principal test(Principal principal){
        return principal;
    }

}
