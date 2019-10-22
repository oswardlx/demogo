package com.example.test;

import com.example.entity.Man;
import com.sun.corba.se.spi.ior.IdentifiableBase;

public class Main {
    public static void main(String [] args){
        Man man = null;
        System.out.println(test2(man.getAge()));
    }

    public static String test2(String str){
        return getSafeStr(str);
    }
    public static String getSafeStr(String str) {
        return isBlank(str) ? "" : str;
    }
    public static boolean isBlank(final String str) {
        return str == null || str.trim().isEmpty();
    }
}
