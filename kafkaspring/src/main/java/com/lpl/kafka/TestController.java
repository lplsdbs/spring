package com.lpl.kafka;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

@Controller
public class TestController {
    @Autowired
    private KafkaTemplate<String,String>kafkaTemplate;
    @RequestMapping(value = "/trade-entrust",method = {RequestMethod.GET})
    public void  kafkaProducer(String pa, HttpServletRequest request, HttpServletResponse response){
        PrintWriter writer= null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String msg=pa.toString();
        kafkaTemplate.sendDefault(msg);

        writer.print("success");
    }
    @RequestMapping(value = "/lpl")
    public void test(){
        System.out.println("12312");
    }
}

