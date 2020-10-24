package com.other;

public class JavaTest {
    //求1-100的和
    private static int sumNum(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sumNum(n-1);
    }
    //用递归求解斐波拉且数列
    public static int fib(int n) {
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        return fib(n - 1) + fib(n - 2);}
    //用循环求解
    public static int fib2(int n) {
        int a = 0, b = 1, c = 1;
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        for (int i = 0; i < n - 1; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
    //用数组求解
    public static int fib3(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }
    //用递归计算阶乘
    public static int jc(int n)
    {
        //结束条件
        if ( n == 1)
            return 1;
        //递归条件
        return n * jc(n-1);
    }
    //用for循环实现阶乘
    public static int jc2(int n)
    {

        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
        }
        return sum;
    }
//    递归的条件:
//            1、 结束条件: 必须有一个明确的递归结束条件，称为递归出口。
//            2、 递归条件: 递归的运算法则.
//            递归的特点:
//            1、简洁明了： 递归算法，一般让人一眼就能看出运算结构，很接近于数学自然语言。
//            2、内存消耗大：在递归调用的过程当中系统为每一层的返回点、局部量等开辟了栈来存储。递归次数过多容易造成栈溢出等。所以一般不提倡用递归算法设计程序。

}
