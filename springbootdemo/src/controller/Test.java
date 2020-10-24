package controller;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by as on 2018/8/16.
 */
@Controller
//springboot启动时会自动注入数据源和配置jpa
//解决办法一：启动类中加入注解：@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
//springboot项目启动类不能直接放到src下面否则就会报错
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
//SpringBootApplication注解是SpringBoot的核心注解是入口
@Configuration//这是一个spring的配置xml
//@ComponentScan//：默认扫描@SpringBootApplication所在类的同级目录以及它的子目录。
//@EnableAutoConfiguration
//：启用自动配置，该注解会使SpringBoot根据项目中依赖的jar包自动配置项目的配置项：
// a)如：我们添加了spring-boot-starter-web的依赖，项目中也就会引入SpringMVC的依赖，SpringBoot就会自动配置tomcat和SpringMVC
public class Test {
    @RequestMapping("hello")
    @ResponseBody
    public String demo(){
        return "hello word";
    }

    public static void main(String[] args) {
        SpringApplication  app=new SpringApplication(Test.class);
        app.setBannerMode(Banner.Mode.OFF);//关闭启动的banner,banner.txt放在resource下面启动就会自动加载
        app.run(args);


    }
}

