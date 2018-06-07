package com.example.wselsfk.util;


import com.example.wselsfk.constant.SessionResult;
import com.example.wselsfk.exception.ParamErrorException;

import javax.websocket.CloseReason;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhaoLai Huang on 2018/4/9.
 */
public class WebSocketUtils {
    public static Map<String,String> catchQueryParames(Session session){
        Map m = new HashMap();
        String parames = session.getQueryString();
        if(StringUtils.isNotBlank(parames)) {
            try{
                String[] paramesArray = parames.split("&");
                for (String parame : paramesArray) {
                    String[] parameArray = parame.split("=");
                    if (parameArray != null && parameArray.length == 2) {
                        m.put(parameArray[0], parameArray[1]);
                    }
                }
            }catch (Exception e){
                throw new ParamErrorException("截取参数异常",e);
            }
        }
        return m;
    }

    public static void close(Session session, SessionResult result) throws Exception {
        CloseReason reason = new CloseReason(CloseReason.CloseCodes.getCloseCode(result.getCode()),result.getReason());
        session.close(reason);
    }

    public static void sendMessage(Session session,String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

}
