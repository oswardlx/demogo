package com.example.test;

import com.example.entity.Man;
import com.sun.corba.se.spi.ior.IdentifiableBase;

import javax.swing.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        Man man = null;
//        System.out.println(test2(man.getAge()));
//        System.out.println(isBlank2(null));
//        StringBuilder sBuilder = new StringBuilder("");

        Map<String,String>  param = new HashMap<>();
        param.put("g1","g1Param");
        param.put("g2","g2Param");
        param.put("g3","g3Param");
        param.put("g4","g4Param");
        String ggog = "{g1}+{g2}+{g3}+{g4}";
        List<String> gggg = getKeyList(ggog);
//        String[] g1 = ggog.split("\\{|\\}");
        for(String g : gggg){
            ggog = ggog.replace("{"+g+"}",param.get(g));
//            System.out.println(param.get(g));
        }

        System.out.println(ggog);
//        System.out.println(9*6/3);
//        System.out.println(sBuilder.substring(0, 0));
    }
    public static List<String> getKeyList (String input ){
        if(input== null || input.length()<1){
            return null;
        }
        List<String> result = new ArrayList<>();
        char[] inputCharArr = input.toCharArray();
        int startIndex = 0;
        int endIndex = 0;
        boolean openFlag = false;
        for(int x = 0; x<inputCharArr.length;x++){
            if('{' == inputCharArr[x]){
                startIndex = x;
                openFlag = true;
            }else if(openFlag && '}' == inputCharArr[x]){
                endIndex = x;
                openFlag = false;
                result.add(input.substring(startIndex+1,endIndex));
            }
        }
        return result;
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
