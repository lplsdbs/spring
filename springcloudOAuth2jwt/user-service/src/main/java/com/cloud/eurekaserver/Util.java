package com.cloud.eurekaserver;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Util {

    private static final BCryptPasswordEncoder b=new BCryptPasswordEncoder();
    public static String BcryptPassword(String password){
        return b.encode(password);
    }
    public static boolean matchs(CharSequence charSequence,String en){
        return b.matches(charSequence,en);
    }
}
