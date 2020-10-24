package com.other;

import redis.clients.jedis.Jedis;

public class TryAction {

    static int fa(int i){
        if(i==0){
            return 1;
        }else {
            return i*fa(i-1);
            //5*4*3*2*1
        }
    }
    public static void main(String[] args)throws Exception {
//        System.out.println(fa(5));
//        System.out.println(fib(6));
//        for(int i=0;i<5;i++){
//            System.out.println(i);
//            System.out.println(i);
//        }
//        int j=0;
//        System.out.println("---------");
//        for(;;){
////            j++;
//            System.out.println(j++);
////            if(j>10){
////                break;
////            }
//        }
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.1.8",6379);
        System.out.println("Connection to server sucessfully");
        //查看服务是否运行
        System.out.println("Server is running:" + jedis.ping());

    }

    public static int fib(int n)throws Exception  {
        if (n < 0)
            throw new Exception("参数不能为负！");
//            System.out.println("e");
        else if (n == 0 || n == 1)
            return n;
        else
            return fib(n - 1) + fib(n - 2);
    }
}
