package com.example.zookeeper_boot;

import com.example.zookeeper_boot.zk.Register;
import com.example.zookeeper_boot.zk.ZookListener;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
@RestController
public class ZookeeperBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperBootApplication.class, args);
    }

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ZookListener listener;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ZookListener zookListener(){
        ZookListener listener = new ZookListener();
        listener.init();
        return listener;
    }

    @RequestMapping(value = "/order/id",method = RequestMethod.GET)
    public String get(){
        //从zookeeper中获取调用的ip
        String path = listener.getPath();
        if(path == null){
            return "对不起，产品暂时停止服务！";
        }
        return restTemplate.getForObject("http://"+listener.getPath()+"/product/id",String.class);
    }

}
