package com.ccb.portal;

import com.ccb.portal.util.SpringContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.DispatcherServlet;

import com.ccb.portal.entity.ConfigConstant;
import com.ccb.portal.filter.MyDispatcherServlet;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties( {ConfigConstant.class})
//@ImportResource({"classpath:spring/spring-Context.xml"}) //加入spring的bean的xml文件
public class PortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalApplication.class, args);
	}

	/*
	* 重写request考虑后期是否对页面参数解密，这里统一解密，会将json格式的参数转换为key-value
	* */
//	@Bean
//	@Qualifier(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
//	public DispatcherServlet dispatcherServlet(){
//		return new MyDispatcherServlet();
//	}
}
