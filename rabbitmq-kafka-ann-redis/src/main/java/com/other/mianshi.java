package com.other;

import org.junit.Test;

public class mianshi {
    //阶乘
    public static long factorial(int n) throws Exception  {
        if (n < 0)
            throw new Exception("参数不能为负！");
//            System.out.println(" ");
         else if (n == 1 || n == 0)
            return 1;
        else
            return n * factorial(n - 1);
    }

    public static void main(String[] args)throws Exception {
//        System.out.println(factorial(3));
//        recursion_display(6);
        System.out.println(fib(6));
    }
    /**
     * 关于 递归中 递进和回归的理解
     * @param n
     */
    public static void recursion_display(int n) {
        int temp=n;//保证前后打印的值一样
        System.out.println("递进:" + temp);
        if (n > 0) {
            recursion_display(--n);
        }
        System.out.println("回归:" + temp);
    }
    //斐波拉且数列
    public static int fib(int n) throws Exception {
        if (n < 0)
            throw new Exception("参数不能为负！");
        else if (n == 0 || n == 1)
            return n;
        else
            return fib(n - 1) + fib(n - 2);
    }

}
