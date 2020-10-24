package com.ccb.portal.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



/**
 * 
 * @author wuheng
 *	token 拦截器
 *  获取请求头中的token,将其放入响应头中
 */
//@Configuration
public class TokenInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

   

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
    	String token=request.getHeader("Authorization");
        response.addHeader("Authorization", token);
    	return super.preHandle(request, response, handler);
    }
}
