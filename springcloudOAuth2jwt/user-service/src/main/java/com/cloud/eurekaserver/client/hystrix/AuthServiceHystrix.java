package com.cloud.eurekaserver.client.hystrix;


import com.cloud.eurekaserver.client.AuthServiceClient;
import com.cloud.eurekaserver.entity.JWT;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password,String scope) {
        System.out.println(authorization+"  "+type);
        return null;
    }
}
