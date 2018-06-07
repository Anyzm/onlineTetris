package com.example.wselsfk.service.imp;

import com.example.wselsfk.cache.GameCache;
import com.example.wselsfk.constant.SystemCodeMsg;
import com.example.wselsfk.constant.SystemConstant;
import com.example.wselsfk.dto.ReturnResult;
import com.example.wselsfk.entity.GameRoom;
import com.example.wselsfk.entity.User;
import com.example.wselsfk.exception.ConnectException;
import com.example.wselsfk.service.UserService;
import com.example.wselsfk.util.GameUtils;
import com.example.wselsfk.util.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by ZhaoLai Huang on 2018/4/10.
 */
public class UserServiceImp implements UserService {
    @Override
    public ReturnResult login(User user) {
        return null;
    }

    @Override
    public ReturnResult createRoom(GameRoom gameRoom) {
        return null;
    }

    @Override
    public List<GameRoom> searchAllGameRooms() {
        return GameCache.getOpenGameRooms();
    }

    @Override
    public GameRoom searchGameRoomByType(String type, String parame) {
        if(SystemConstant.SEARCH_TYPE_GAMEROOMID.equals(type)){
            return GameCache.getGameRoomById(parame);
        }else if(SystemConstant.SEARCH_TYPE_USERID.equals(type)){
            return GameCache.userIdGameRoom.get(parame);
        }else if(SystemConstant.SEARCH_TYPE_USERNAME.equals(type)){
            User user = GameCache.getUserByUserName(parame);
            if(user != null && StringUtils.isNotBlank(user.getUserId())){
                return GameCache.userIdGameRoom.get(user.getUserId());
            }
        }
        return null;
    }

    @Override
    public ReturnResult joinRoomById(String userId, String gameRoomId) {
        ReturnResult result = new ReturnResult(ReturnResult.CodeMessage.SUCCESS);
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(gameRoomId)){
            result = new ReturnResult(ReturnResult.CodeMessage.ERROR_PARAME);
        }else{
            User user = GameCache.getUserById(userId);
            GameRoom gameRoom = GameCache.getGameRoomById(gameRoomId);
            if(user == null){
                result = new ReturnResult(ReturnResult.CodeMessage.NO_LOGIN);
            }else if(gameRoom == null){
                result = new ReturnResult(ReturnResult.CodeMessage.ROOM_NO_EXSITANT);
            }else{
                if(gameRoom.getJoiner() != null && StringUtils.isNotBlank(gameRoom.getJoiner().getUserName())){
                    result = new ReturnResult(ReturnResult.CodeMessage.ROOM_FULL);
                }else if(gameRoom.getOwner()!=null && userId.equalsIgnoreCase(gameRoom.getOwner().getUserId())){
                    result = new ReturnResult(ReturnResult.CodeMessage.CANNOT_JOIN_SELFROOM);
                }else{
                    long time = System.currentTimeMillis();
                    gameRoom.setJoiner(user);
                    gameRoom.setStartTime("" + time);
                    gameRoom.setGameState(GameRoom.GameState.ING.getState());
                    GameCache.userIdGameRoom.put(userId,gameRoom);
                    try {
                        GameUtils.sendSystemMessageToRoomUser(gameRoom, SystemCodeMsg.START_GAME);
                    } catch (IOException e) {
                        throw new ConnectException(SystemConstant.CONNECT_EXCEPTION.getSystemConstant());
                    }
                }
            }
        }
        return result;
    }
}
