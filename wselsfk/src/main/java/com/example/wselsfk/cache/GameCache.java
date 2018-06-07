package com.example.wselsfk.cache;

import com.example.wselsfk.entity.GameRoom;
import com.example.wselsfk.entity.User;
import com.example.wselsfk.util.StringUtils;
import com.example.wselsfk.util.comparator.GameRoomComparator;
import com.example.wselsfk.util.comparator.UserComparator;

import javax.websocket.Session;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by ZhaoLai Huang on 2018/4/9.
 */
public class GameCache {

    /**
     * websocket的总链接数
     */
    private static int wsCount = 0;

    /**
     *  当前ws连接
     *  userId-Session
     */
    public static Map<String,Session> userIdSession = new ConcurrentHashMap<String,Session>();

    /**
     * 当前游戏房间
     * userId-GameRoom
     */
    public static Map<String,GameRoom> userIdGameRoom = new ConcurrentHashMap<String,GameRoom>();

    /**
     * 所有用户
     */
    public static Set<User>  users = new ConcurrentSkipListSet<User>(new UserComparator());

    /**
     * 所有游戏房间
     */
    public static Set<GameRoom>  gameRooms = new ConcurrentSkipListSet<GameRoom>(new GameRoomComparator());


    public static User getUserById(String userId){
        if(StringUtils.isBlank(userId)){
            return null;
        }
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(userId.equals(user.getUserId())){
                return user;
            }
        }
        return null;
    }

    public static User getUserByUserName(String userName){
        if(StringUtils.isBlank(userName)){
            return null;
        }
        Iterator<User> it = users.iterator();
        while(it.hasNext()){
            User user = it.next();
            if(userName.equals(user.getUserName())){
                return user;
            }
        }
        return null;
    }

    public static GameRoom getGameRoomById(String roomId){
        if(StringUtils.isBlank(roomId)){
            return null;
        }
        Iterator<GameRoom> it = gameRooms.iterator();
        while(it.hasNext()){
            GameRoom gameRoom = it.next();
            if(roomId.equals(gameRoom.getId())){
                return gameRoom;
            }
        }
        return null;
    }

    public static List<GameRoom> getOpenGameRooms(){
        List<GameRoom> list = new ArrayList<GameRoom>();
        Iterator<GameRoom> it = gameRooms.iterator();
        while(it.hasNext()){
            GameRoom gameRoom = it.next();
            if(gameRoom != null){
                if(gameRoom.isOpenToAll()){
                    list.add(gameRoom);
                }
            }
        }
        return list;
    }

    public static synchronized void wsCountAdd(int num){
        wsCount += num;
    }

    public static synchronized void  wsCountAdd(){
        wsCount++;
    }

    public static synchronized void wsCountReduce(){
        wsCount--;
    }

    public static synchronized void wsCountReduce(int num){
        wsCount -= num;
    }
}
