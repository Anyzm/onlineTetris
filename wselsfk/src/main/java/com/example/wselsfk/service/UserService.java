package com.example.wselsfk.service;

import com.example.wselsfk.dto.ReturnResult;
import com.example.wselsfk.entity.GameRoom;
import com.example.wselsfk.entity.User;

import java.util.List;

/**
 * Created by ZhaoLai Huang on 2018/4/10.
 */
public interface UserService {
    public ReturnResult login(User user);

    public ReturnResult createRoom(GameRoom gameRoom);

    public List<GameRoom> searchAllGameRooms();

    public GameRoom searchGameRoomByType(String type,String parame);

    public ReturnResult joinRoomById(String userId,String gameRoomId);
}
