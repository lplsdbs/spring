package com.lpl.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * 测试继承AbstractTransactionalSpringContextTests这个类
 * 继承该类，可以测试进行事务控制，测试完成后自动回滚
 * */
@RunWith(SpringJUnit4ClassRunner.class)
//locations:参数值因配置文件地址来改变
@ContextConfiguration(locations={"classpath:spring/Spring-Context.xml"})
public class SpringJunitTest extends AbstractJUnit4SpringContextTests {
    //注入service对象
    @Autowired
    private KafkaTemplate<String,String>kafkaTemplate;
    @Test
    //还可以加入@RollBack注解 @Transaction注解来对方法进行事务注解
    public void kafkaProducerTest() throws Exception {
      kafkaTemplate.sendDefault("刘彭亮");
    }
}
