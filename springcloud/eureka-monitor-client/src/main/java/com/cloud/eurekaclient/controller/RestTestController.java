//package com.cloud.eurekaclient.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
////feign用于调用远程其他服务，feignclient具有负载均衡的能力
////ribbon和feign都可以去消费服务实现负载均衡的目的
//@RestController
//public class RestTestController {
//    @Autowired
//    HiService hiService;
//    @GetMapping("/hi")
//    //消费注册的服务
//    public String test2(@RequestParam(required =false,defaultValue = "forezp")String name){
//       // RestTemplate restTemplate=new RestTemplate();
//        return  hiService.sayHi(name);
//    }
//}
