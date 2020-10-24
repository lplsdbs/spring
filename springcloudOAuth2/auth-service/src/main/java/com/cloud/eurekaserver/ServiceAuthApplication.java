package com.cloud.eurekaserver;

 import com.cloud.eurekaserver.service.impl.UserService;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
 import org.springframework.security.core.userdetails.UserDetailsService;
 import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@SpringBootApplication
//资源服务
@EnableResourceServer
@EnableEurekaClient
public class ServiceAuthApplication {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }


    @Configuration
    //开启授权服务功能
    @EnableAuthorizationServer
    protected  class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

        //private TokenStore tokenStore = new InMemoryTokenStore();

        JdbcTokenStore tokenStore=new JdbcTokenStore(dataSource);

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserService userServiceDetail;

//        http://localhost:5000/uaa/oauth/token
        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

            clients.inMemory()
                    .withClient("browser")
                    .authorizedGrantTypes("refresh_token", "password")
                    .scopes("ui")
                    .and()
                    .withClient("service-hi")
                    .secret("123456")
                    .authorizedGrantTypes("client_credentials", "refresh_token","password")
                    .scopes("server");

        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    //数据库存储token
                    .tokenStore(tokenStore)
                    .authenticationManager(authenticationManager)
                    .userDetailsService(userServiceDetail);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");

        }
    }
}
