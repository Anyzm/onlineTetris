package com.example.wselsfk.util.comparator;

import com.example.wselsfk.entity.User;

import java.util.Comparator;

/**
 * Created by ZhaoLai Huang on 2018/4/9.
 */

/**
 * 按名称的自然顺序排列
 */
public class UserComparator implements Comparator<User>{

    @Override
    public int compare(User o1, User o2) {
        if(o1 == o2){
            return 0;
        }else if(o1 != null){
            if(o2 != null){
                return o1.getUserName().compareToIgnoreCase(o2.getUserName());
            }else{
                return 1;
            }
        }else{
            if(o2 != null){
                return -1;
            }else{
                return 0;
            }
        }
    }

}
