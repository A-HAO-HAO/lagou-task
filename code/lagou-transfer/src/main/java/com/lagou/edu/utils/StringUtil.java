package com.lagou.edu.utils;

public class StringUtil {

    public static String firstCharToLowerCase(String str){
        if(str == null || str.length() < 1){
            return str;
        }
        return str.substring(0, 1).toLowerCase()+str.substring(1);
    }

}
