package com.example.test;

import com.example.entity.Man;
import com.sun.corba.se.spi.ior.IdentifiableBase;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        Man man = null;
//        System.out.println(test2(man.getAge()));
//        System.out.println(isBlank2(null));
//        StringBuilder sBuilder = new StringBuilder("");
        String ggog = "{g1}1{g2}2{g3}3{g4}4";
        String[] g1 = ggog.split("\\{([^{]*)\\}");
        for(String g : g1){
            System.out.println(g.toString());
        }
        System.out.println(9*6/3);
//        System.out.println(sBuilder.substring(0, 0));
    }

    public static String test2(String str) {
        return getSafeStr(str);
    }

    public static String getSafeStr(String str) {
        return isBlank(str) ? "" : str;
    }

    public static boolean isBlank(final String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isBlank2(String str){
        if(str.isEmpty()){
            return true;
        }
        return false;
    }
}
