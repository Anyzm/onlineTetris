package com.example.wselsfk.controller;

import com.example.wselsfk.cache.GameCache;
import com.example.wselsfk.conffig.Cors;
import com.example.wselsfk.constant.SystemConstant;
import com.example.wselsfk.dto.ReturnResult;
import com.example.wselsfk.entity.GameRoom;
import com.example.wselsfk.entity.User;
import com.example.wselsfk.exception.ParamErrorException;
import com.example.wselsfk.service.UserService;
import com.example.wselsfk.service.imp.UserServiceImp;
import com.example.wselsfk.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.websocket.Session;
import java.util.List;

/**
 * Created by ZhaoLai Huang on 2018/4/10.
 */
@CrossOrigin(origins = "http://localhost:8088",maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController extends Cors {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService = new UserServiceImp();

    @RequestMapping(value = "/login")
    @ResponseBody
    public ReturnResult login(@RequestBody User user){
        ReturnResult result = new ReturnResult(ReturnResult.CodeMessage.SUCCESS);
        String userId = user.getUserId();
        if(StringUtils.isBlank(userId)){
            result = new ReturnResult(ReturnResult.CodeMessage.ERROR_PARAME);
            return result;
        }else{
            GameCache.users.add(user);
            logger.info("用户:"+userId+"登陆成功！");
        }
        return result;
    }

    @RequestMapping(value = "/createRoom")
    @ResponseBody
    public ReturnResult createRoom(@RequestParam(name = "userId") String userId, @RequestParam(name = "open") String open,
                                   @RequestParam(name = "password",required = false) String password){
        ReturnResult result = new ReturnResult(ReturnResult.CodeMessage.SUCCESS);
        if(StringUtils.isNotBlank(userId)){
            User user = GameCache.getUserById(userId);
            if(user == null){
                result = new ReturnResult(ReturnResult.CodeMessage.NO_LOGIN);
                return result;
            }
            Session session = GameCache.userIdSession.get(userId);
            if(session == null){
                result = new ReturnResult(ReturnResult.CodeMessage.CONNECTION_EXCEPTION);
                logger.info("用户:"+userId+"建房失败，连接异常");
                return result;
            }
            GameRoom gameRoomx = GameCache.userIdGameRoom.get(userId);
            if(gameRoomx != null){
                result = new ReturnResult(ReturnResult.CodeMessage.ROOM_EXIST);
                logger.info("用户:"+userId+"建房失败，重复建房");
                return result;
            }
            long time = System.currentTimeMillis();
            GameRoom gameRoom = new GameRoom();
            gameRoom.setId(StringUtils.createUniqueId());
            gameRoom.setOwner(user);
            gameRoom.setGameState(GameRoom.GameState.WAIT.getState());
            gameRoom.setCreateTime("" + time);
            if(StringUtils.isBlank(open) || Boolean.valueOf(open)){
                gameRoom.setOpenToAll(true);
            }else{
                gameRoom.setOpenToAll(false);
                gameRoom.setPassword(password);
            }
            GameCache.userIdGameRoom.put(userId,gameRoom);
            GameCache.gameRooms.add(gameRoom);
            logger.info("用户:"+userId+"创建房间："+gameRoom+"成功！");
        }else{
            result = new ReturnResult(ReturnResult.CodeMessage.ERROR_PARAME);
            return result;
        }
        return result;
    }

    @GetMapping(value = "/findRoom")
    public GameRoom searchGameRoomByUserId(@RequestParam(name = "type") String type,@RequestParam(name = "value") String value){
        if(SystemConstant.SEARCH_TYPE_USERID.getSystemConstant().equalsIgnoreCase(type)){
            return userService.searchGameRoomByType(SystemConstant.SEARCH_TYPE_USERID.getSystemConstant(),value);
        }else if(SystemConstant.SEARCH_TYPE_USERNAME.getSystemConstant().equalsIgnoreCase(type)){
            return userService.searchGameRoomByType(SystemConstant.SEARCH_TYPE_USERNAME.getSystemConstant(),value);
        }else if(SystemConstant.SEARCH_TYPE_GAMEROOMID.getSystemConstant().equalsIgnoreCase(type)){
            return userService.searchGameRoomByType(SystemConstant.SEARCH_TYPE_GAMEROOMID.getSystemConstant(),value);
        }else{
           throw new ParamErrorException(SystemConstant.PARAME_ERROR.getSystemConstant());
        }
    }
    @GetMapping(value = "/findAllRoom")
    public List<GameRoom> searchAllOpenGameRooms(){
        return userService.searchAllGameRooms();
    }

    @GetMapping(value = "/joinRoom")
    public ReturnResult joinGameRoomById(@RequestParam(name = "userId")String userId,@RequestParam(name = "roomId")String gameRoomId){
        ReturnResult result;
        Session session = GameCache.userIdSession.get(userId);
        if(session == null){
            result = new ReturnResult(ReturnResult.CodeMessage.CONNECTION_EXCEPTION);
            logger.info(userId + "加入房间"+gameRoomId+"失败");
            return result;
        }
        GameRoom gameRoomx = GameCache.userIdGameRoom.get(userId);
        if(gameRoomx != null){
            result = new ReturnResult(ReturnResult.CodeMessage.ROOM_EXIST);
            logger.info("用户:"+userId+"建房失败，重复建房");
            return result;
        }
        return userService.joinRoomById(userId,gameRoomId);
    }
}
