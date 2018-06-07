package com.example.wselsfk.util;

import com.example.wselsfk.cache.GameCache;
import com.example.wselsfk.constant.SystemCodeMsg;
import com.example.wselsfk.constant.SystemConstant;
import com.example.wselsfk.entity.GameRoom;
import com.example.wselsfk.entity.Message;
import org.springframework.boot.web.embedded.netty.NettyWebServer;

import javax.lang.model.element.NestingKind;
import javax.websocket.Session;
import java.io.IOException;

/**
 * create by Anyzm on 2018/4/15
 */
public class GameUtils {
    public static void sendSystemMessageToRoomUser(GameRoom gameRoom, SystemCodeMsg  codeMsg) throws IOException {
        Message msg = new Message();
        msg.setId(StringUtils.createUniqueId());
        msg.setCode(codeMsg.getCode());
        msg.setMsgType(SystemConstant.MSG_TYPE_SYSTEM.getSystemConstant());
        msg.setContent(codeMsg.getMsg());
        String res = StringUtils.gson.toJson(msg);
        if(gameRoom.getOwner() != null) {
            Session session1 = GameCache.userIdSession.get(gameRoom.getOwner().getUserId());
            if(session1 != null && session1.isOpen()) {
                WebSocketUtils.sendMessage(session1, res);
            }
            if(gameRoom.getJoiner() != null) {
                Session session2 = GameCache.userIdSession.get(gameRoom.getJoiner().getUserId());
                if(session2 != null && session2.isOpen()) {
                    WebSocketUtils.sendMessage(session2, res);
                }
            }

        }
    }

}
