package com.example.redis_boot;

import com.example.redis_boot.redis.JedisClientCluster;
import com.example.redis_boot.redis.RedisConfig;
import com.example.redis_boot.redis.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootApplication
@RestController
public class RedisBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisBootApplication.class, args);
    }



    @Autowired
    private RedisProperties redisProperties;

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private JedisClientCluster jedisClientCluster;

    @RequestMapping(value = "getRedisValue")
    public String getRedisValue(){
        System.out.println(redisProperties.toString());
        System.out.println(redisConfig.getJedisCluster().getClusterNodes());
        System.out.println(jedisClientCluster.get("yp"));
        jedisClientCluster.set("12","12");
        System.out.println(jedisClientCluster.get("12"));
        return jedisClientCluster.get("12");
    }


}
