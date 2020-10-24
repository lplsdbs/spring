package com.other;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Test(String name){
        this.name=name;
    }
    public Test( ){

    }


        public static void main(String[] args)throws Exception {
//        List<Test>list=new ArrayList<>();
//        for(int i=0;i<10;i++){
//            Test test=new Test(i+"");
//            list.add(test);
//        }
//        list.forEach(Test::test);
        Thread thread=new Thread(()->  {
            System.out.println("11");
            System.out.println("af");
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();
        System.out.println(thread.isInterrupted());
//        thread.join();
//        (int j)->(return 5*j);
        //lambad表达式，函数接口（只有一个方法的接口），匿名类方法
        test t=(k,l)->{
            System.out.println(k+l);
            return 2;
        };
        System.out.println(t.lpl("q","w"));
        System.out.println();
        lambad("d","ds",(s,l)->test(s,l));//lambad接口的匿名实现以及方法体的匿名实现针对函数接口编程
        List<String>list=new ArrayList<String>();
        for(int i=0;i<5;i++){
            list.add(i+"");
        }
        list.forEach(x-> System.out.println(x));
    }
    interface  test{
        int lpl(String s1, String s2);
    }

    public static   void lambad(String s,String l,test t ){
        System.out.println(t.lpl(s,l));
    }
    public void test(){
        System.out.println("eee");
    }
    public static int test(String namaa,String lo){
        return 500;
    }
    @Override
    public String toString() {
        return this.name;
    }

    public static void la(String s){
        System.out.println(s);
    }
//    @Test

}
