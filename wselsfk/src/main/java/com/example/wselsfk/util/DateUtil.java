package com.example.wselsfk.util;

import java.text.SimpleDateFormat;

/**
 * Created by ZhaoLai Huang on 2018/4/10.
 */
public class DateUtil {
    public  static final ThreadLocal<SimpleDateFormat>sdf_yyyyMMdd=
            new  ThreadLocal<SimpleDateFormat>(){
                @Override
                protected SimpleDateFormat initialValue(){
                    return new SimpleDateFormat("yyyy-MM-dd");
                }
            };
    public  static final ThreadLocal<SimpleDateFormat>sdf_yyyyMMdd_hhmmss=
            new  ThreadLocal<SimpleDateFormat>(){
                @Override
                protected SimpleDateFormat initialValue(){
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
            };
    public  static final ThreadLocal<SimpleDateFormat>sdf_yyyyMMddhhmmss=
            new  ThreadLocal<SimpleDateFormat>(){
                @Override
                protected SimpleDateFormat initialValue(){
                    return new SimpleDateFormat("yyyyMMddHHmmss");
                }
            };
    public  static final ThreadLocal<SimpleDateFormat>sdf_yyyyMMddhhmmssSss=
            new  ThreadLocal<SimpleDateFormat>(){
                @Override
                protected SimpleDateFormat initialValue(){
                    return new SimpleDateFormat("yyyyMMddHHmmssSSS");
                }
            };
    public  static final ThreadLocal<SimpleDateFormat>sdf_4y2M2d=
            new  ThreadLocal<SimpleDateFormat>(){
                @Override
                protected SimpleDateFormat initialValue(){
                    return new SimpleDateFormat("yyyyMMdd");
                }
            };
    public  static final ThreadLocal<SimpleDateFormat>format_second=
            new  ThreadLocal<SimpleDateFormat>(){
                @Override
                protected SimpleDateFormat initialValue(){
                    return new SimpleDateFormat("yyyyMMdd HH:mm:ss");
                }
            };
}
