package com.cloud.eurekaserver.config;

import feign.RequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
//开启client
@EnableOAuth2Client
@EnableConfigurationProperties
@Configuration
public class OAuth2ClientConfig {
    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    //配置受保护的资源
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails(){
        return new ClientCredentialsResourceDetails();
    }
    @Bean
    //配置过滤器存储当前请求以及上下文
    RequestInterceptor requestInterceptor(){
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(),clientCredentialsResourceDetails());
    }
    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(){
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }
}
