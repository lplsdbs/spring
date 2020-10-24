package com.lpl.rabbitmq;

import java.util.concurrent.TimeUnit;

public class ThreadTest {
    public static void main(String[] args) throws Exception{
        System.out.println("begin");
        new Thread(){
            @Override
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sont");
            }
        }.start();
        System.out.println("end");
    }
}
