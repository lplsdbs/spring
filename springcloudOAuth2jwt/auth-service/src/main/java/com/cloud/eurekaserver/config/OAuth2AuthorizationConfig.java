package com.cloud.eurekaserver.config;

import com.cloud.eurekaserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
//开启授权服务功能
@EnableAuthorizationServer
public   class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    //private TokenStore tokenStore = new InMemoryTokenStore();

//    JdbcTokenStore tokenStore=new JdbcTokenStore(dataSource);

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


    //        http://localhost:5000/uaa/oauth/token
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory()
                .withClient("user-service")
                .secret("123456")
                .authorizedGrantTypes("client_credentials", "refresh_token","password")
                .scopes("service")
        .accessTokenValiditySeconds(3600);
        //token过期事件3600秒

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .tokenEnhancer(jwtAccessTokenConverter()).authenticationManager(authenticationManager);
    }
    public TokenStore tokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
    }
    @Bean
    protected JwtAccessTokenConverter jwtAccessTokenConverter(){
        //jks作为token的加密密钥
        KeyStoreKeyFactory keyStoreKeyFactory=new KeyStoreKeyFactory(new ClassPathResource("fzp-jwt.jks"),"fzp123".toCharArray());
        JwtAccessTokenConverter converte=new JwtAccessTokenConverter();
        converte.setKeyPair(keyStoreKeyFactory.getKeyPair("fzp-jwt"));
        return converte;
    }

}
