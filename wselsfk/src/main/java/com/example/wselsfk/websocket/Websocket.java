package com.example.wselsfk.websocket;

import com.example.wselsfk.cache.GameCache;
import com.example.wselsfk.constant.SessionResult;
import com.example.wselsfk.constant.SystemCodeMsg;
import com.example.wselsfk.constant.SystemConstant;
import com.example.wselsfk.entity.GameRoom;
import com.example.wselsfk.entity.Message;
import com.example.wselsfk.entity.User;
import com.example.wselsfk.exception.ConnectException;
import com.example.wselsfk.util.StringUtils;
import com.example.wselsfk.util.WebSocketUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ZhaoLai Huang on 2018/4/9.
 */
@ServerEndpoint(value = "/websocket")
@Component
public class Websocket {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(Websocket.class);

    /**
     * 连接已建立
     * @param session
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config ) {
        Map<String,String> parames = WebSocketUtils.catchQueryParames(session);
        String userId = parames.get("userId");
        if(StringUtils.isBlank(userId)){
            //没有userId，不知道谁连上来了，就把他T了
            try {
                WebSocketUtils.close(session, SessionResult.ERROR_PARAME);
            } catch (Exception e) {
                throw new ConnectException(SystemConstant.CONNECT_EXCEPTION.getSystemConstant());
            }
            return;
        }
        Session sessionx = GameCache.userIdSession.get(userId);
        if(sessionx!=null && sessionx.isOpen()){
            try {
                WebSocketUtils.close(session, SessionResult.REPEAT_LOGIN);
            } catch (Exception e) {
                throw new ConnectException(SystemConstant.CONNECT_EXCEPTION.getSystemConstant());
            }
            return;
        }
        GameCache.userIdSession.put(userId,session);
        GameCache.wsCountAdd();
        System.out.println("连接上来了:" + session.getQueryString() + "," + session);
    }

    /**
     * 连接已关闭
     */
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        Map<String,String> parames = WebSocketUtils.catchQueryParames(session);
        String userId = parames.get("userId");
        if(StringUtils.isNotBlank(userId)){
            logger.info(userId + "连接已断开");
            GameCache.userIdSession.remove(userId);
            for(User user:GameCache.users){
                if( userId.equals(user.getUserId()) ){
                    GameCache.users.remove(user);
                }
            }
            GameRoom gameRoom = GameCache.userIdGameRoom.get(userId);
            if(gameRoom != null){
                User rival = gameRoom.getRival(userId);
                if(rival != null) {
                    Session sessionx = GameCache.userIdSession.get(rival.getUserId());
                    if(sessionx != null && sessionx.isOpen()){
                        Message msg = new Message();
                        msg.setMsgType(SystemConstant.MSG_TYPE_SYSTEM.getSystemConstant());
                        msg.setContent(SystemCodeMsg.RIVAL_OUTLINE.getMsg());
                        msg.setCode(SystemCodeMsg.RIVAL_OUTLINE.getCode());
                        msg.setId(StringUtils.createUniqueId());
                        try {
                            WebSocketUtils.sendMessage(sessionx, StringUtils.gson.toJson(msg));
                            logger.info(userId+"掉线，发送消息给"+rival.getUserId());
                        } catch (IOException e) {
                            throw new ConnectException(SystemConstant.CONNECT_EXCEPTION.getSystemConstant());
                        }
                    }
                }
                GameCache.gameRooms.remove(gameRoom);
            }
            GameCache.userIdGameRoom.remove(userId);
        }
        GameCache.wsCountReduce();
    }

    /**
     * 收到消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if(StringUtils.isBlank(message) || "\"\"".equals(message.trim())){
            return ;
        }
        Map<String,String> parames = WebSocketUtils.catchQueryParames(session);
        String userId = parames.get("userId");
        if(StringUtils.isBlank(userId)){
            //没有userId，不知道谁连上来了，就把他T了
            try {
                WebSocketUtils.close(session, SessionResult.ERROR_PARAME);
            } catch (Exception e) {
                throw new ConnectException(SystemConstant.CONNECT_EXCEPTION.getSystemConstant());
            }
            return;
        }
        GameRoom gameRoom = GameCache.userIdGameRoom.get(userId);
        User rival =  gameRoom.getRival(userId);
        if(rival != null && StringUtils.isNotBlank(rival.getUserId())){
            Session rivalSession = GameCache.userIdSession.get(rival.getUserId());
            if(rivalSession != null && rivalSession.isOpen()) {
                try {
                    WebSocketUtils.sendMessage(rivalSession, message);
                    System.out.println(userId + "发送消息[" + message + "]给" + rival);
                } catch (IOException e) {
                    throw new ConnectException(SystemConstant.CONNECT_EXCEPTION.getSystemConstant());
                }
            }
        }
        System.out.println("收到"+ userId +"消息:" + message);
    }

    /**
     * 发生异常
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

    }
}


