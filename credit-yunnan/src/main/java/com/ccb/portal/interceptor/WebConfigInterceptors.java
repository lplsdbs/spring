package com.ccb.portal.interceptor;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfigInterceptors implements WebMvcConfigurer {
    @Bean//提前装载初始化config
    public JwtInterceptor getJwtInterceptor(){
        return new JwtInterceptor();
    }
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getJwtInterceptor()).excludePathPatterns("/credit/limitBack","/credit/xyynXzxkInfo",  "/credit/xyynXzcfInfo", "/credit/xyynHhmdRedInfo", "/credit/xyynHhmdBlaInfo", "/credit/xyynDetail","/credit/xyynIndex");//放掉某些特定不需要校验token的路由
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");//获得请求的token放入response
    }

}
