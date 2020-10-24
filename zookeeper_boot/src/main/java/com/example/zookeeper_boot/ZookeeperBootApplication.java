package com.example.zookeeper_boot;

import com.example.zookeeper_boot.zk.Register;
 import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

@SpringBootApplication
@RestController
public class ZookeeperBootApplication  {
  //private int serverPort;
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperBootApplication.class, args);
    }

    @RequestMapping(value = "/product/id",method = RequestMethod.GET)
    public String  get(HttpServletRequest request){
        return "访问 product  服务端口："+ request.getLocalPort();
    }

    @Bean
    public Register register(){
        Register register = new Register();
        register.register(getAddressAndPort());
        return register;
    }


    private String getAddressAndPort(){
        try {
            Properties properties = new Properties();
            InputStream stream = ZookeeperBootApplication.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(stream);
            Object port = properties.get("server.port");
            String ip=InetAddress.getLocalHost().getHostAddress();//+":"+serverPort;
            return "127.0.0.1:" + port;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


}



}
