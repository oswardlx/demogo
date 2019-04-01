package com.example.util.test;


import org.springframework.util.StringUtils;

public class DataUtil {
    public static String [] separatePath(String path){
        if(StringUtils.isEmpty(path)){
            return null;
        }
        String[] sep = path.split("\\.");
        return new String[]{sep[0],sep[1]};
    }
}
