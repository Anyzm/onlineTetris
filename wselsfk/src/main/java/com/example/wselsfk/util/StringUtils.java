package com.example.wselsfk.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;

import java.util.Date;

/**
 * Created by ZhaoLai Huang on 2018/4/9.
 */
public class StringUtils {

    public static Gson gson =  new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public static boolean isNotEmpty(String s){
        if(s == null){
            return false;
        }else{
            return !"".equals(s);
        }
    }

    public static boolean isEmpty(String s){
        if(s == null){
            return true;
        }else{
            return "".equals(s);
        }
    }

    public static boolean isBlank(String s){
        if(s == null){
            return true;
        }else{
            return "".equals(s.trim());
        }
    }

    public static boolean isNotBlank(String s){
        if(s == null){
            return false;
        }else{
            return !"".equals(s.trim());
        }
    }

    public static String createUniqueId(String id){
        Date date = new Date();
        String result = DateUtil.sdf_yyyyMMddhhmmssSss.get().format(date)+ (int)Math.random()*100;
        if(StringUtils.isNotBlank(id)){
            result += id;
        }
        return result;
    }

    public static String createUniqueId(){
        Date date = new Date();
        String result = DateUtil.sdf_yyyyMMddhhmmssSss.get().format(date)+ (int)Math.random()*100;
        return result;
    }
}
