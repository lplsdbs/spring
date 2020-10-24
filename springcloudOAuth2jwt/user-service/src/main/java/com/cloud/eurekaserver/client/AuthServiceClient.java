package com.cloud.eurekaserver.client;


import com.cloud.eurekaserver.client.hystrix.AuthServiceHystrix;
import com.cloud.eurekaserver.entity.JWT;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 */

@FeignClient(value = "service-auth" ,fallback =AuthServiceHystrix.class)
public interface AuthServiceClient {

    @PostMapping(value = "/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type,
                 @RequestParam("username") String username, @RequestParam("password") String password,String scope);

}



