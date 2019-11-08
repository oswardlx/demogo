package com.example.test;

import com.example.entity.Man;
import com.sun.corba.se.spi.ior.IdentifiableBase;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
//        Man man = null;
//        System.out.println(test2(man.getAge()));
//        System.out.println(isBlank2(null));
//        StringBuilder sBuilder = new StringBuilder("");

//        Map<String,String>  param = new HashMap<>();
//        param.put("g1","g1Param");
//        param.put("g2","g2Param");
//        param.put("g3","g3Param");
//        param.put("g4","g4Param");
//        String ggog = "{g1}+{g2}+{g3}+{g4}";
//        List<String> gggg = getKeyList(ggog);
////        String[] g1 = ggog.split("\\{|\\}");
//        for(String g : gggg){
//            ggog = ggog.replace("{"+g+"}",param.get(g));
////            System.out.println(param.get(g));
//        }
//
//        System.out.println(ggog);
//        System.out.println(9*6/3);
//        System.out.println(sBuilder.substring(0, 0));
//        String gg = "60.3.3";
//        System.out.println(isInteger(gg));
//        File loala
//        1,2,3,4,2,6
        int[] arr1 = new int[]{1,1,2,3,1,4,3};
        int[] arr2 = new int[]{1,2,1,2};

//        7+   arr1.sum


        int index  = 0 ;
        int lastIndex = 0;
        int sum;
        List<Integer> i = new ArrayList<>();
        for(int x : arr1){
            sum = 0;
            for(;index<lastIndex+x;index++){
                sum += arr2[index%arr2.length];
            }
            lastIndex = index;
            i.add(sum);
        }

        for(Integer  it : i){
            System.out.println(it);
        }


    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[\\-|\\+]?\\d+(\\.\\d+)?$");
        return pattern.matcher(str).matches();
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
