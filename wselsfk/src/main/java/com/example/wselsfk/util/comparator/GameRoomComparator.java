package com.example.wselsfk.util.comparator;

import com.example.wselsfk.entity.GameRoom;

import java.util.Comparator;

/**
 * Created by ZhaoLai Huang on 2018/4/9.
 */

/**
 * 先创建的游戏房间排在前面
 */
public class GameRoomComparator implements Comparator<GameRoom> {
    @Override
    public int compare(GameRoom o1, GameRoom o2) {
        if(o1 == o2){
            return 0;
        }else if(o1 != null){
            if(o2 != null){
                return o1.getCreateTime().compareToIgnoreCase(o2.getCreateTime());
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
