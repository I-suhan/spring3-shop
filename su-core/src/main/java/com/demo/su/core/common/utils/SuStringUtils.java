package com.demo.su.core.common.utils;

import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SuStringUtils {

    public static String fuzzyString(String fuzzy){
        return "," + fuzzy + ",";
    }

    public static boolean isNotBlank(String s){
        return !isBlank(s);
    }

    public static boolean isBlank(String s){
        return s == null || s.isEmpty();
    }

    public static String topUpCase(String s){
        return StringUtils.capitalize(s);
    }

    public static List<String> string2List(String s){
        List<String> list;
        if(s.contains("/")){
            String[] split = s.split("/");
            list = Arrays.asList(split);
        }else if(s.contains(",")){
            String[] split = s.split(",");
            list = Arrays.asList(split);
        }else if(s.contains("，")){
            String[] split = s.split("，");
            list = Arrays.asList(split);
        }else if(s.contains("\\")){
            String[] split = s.split("\n");
            list = Arrays.asList(split);
        }else if(isNotBlank(s)){
            list = Collections.singletonList(s);
        }else {
            list = new ArrayList<>();
        }
        return list.stream().map(String::trim).collect(Collectors.toList());
    }

    public static List<String> string2List(String s, String regex){
        String[] split = s.split(regex);
        List<String> list = Arrays.asList(split);
        return list.stream().map(String::trim).collect(Collectors.toList());
    }

    /**********
     *                  去掉数组自带的所有符号
     */
    public static <T> String listToStringWithoutSeparator(List<T> list){
        return listToStringWithSeparator(list,"");
    }

    public static <T> String listToStringWithSeparator(List<T> list, String separator){
        StringBuilder sb = new StringBuilder();
        for (T t : list){
            sb.append(t).append(separator);
        }
        if(isNotBlank(separator)){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static String subStringBefore(String main, String sub){
        return StringUtils.substringBefore(main,sub);
    }

    public static int string2Int(String s){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return Integer.parseInt(sb.toString());
    }
}
