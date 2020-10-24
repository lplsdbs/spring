package com.lpl.annotation;

import java.lang.reflect.Method;

public class UserCaseTracker {
    public static void annoUserCaseTracker(Class<?>c){
        for(Method m:c.getDeclaredMethods()){
            UserCase userCase=m.getAnnotation(UserCase.class);
            System.out.println(userCase.id()+"  "+userCase.desc());
        }
    }

    public static void main(String[] args) {
        annoUserCaseTracker(AnnotainTest.class);
    }
}
