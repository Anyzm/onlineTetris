package com.example.wselsfk.exception;

import com.example.wselsfk.dto.ReturnResult;

/**
 * Created by ZhaoLai Huang on 2018/4/13.
 */
public class RoomFullException extends GameException {
    private ReturnResult.CodeMessage  codeMessage = ReturnResult.CodeMessage.ROOM_FULL;

    public RoomFullException(String message) {
        super(message);
    }

    public RoomFullException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public ReturnResult.CodeMessage getCodeMessage() {
        return codeMessage;
    }

    @Override
    public void setCodeMessage(ReturnResult.CodeMessage codeMessage) {
        this.codeMessage = codeMessage;
    }
}
