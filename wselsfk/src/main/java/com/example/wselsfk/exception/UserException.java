package com.example.wselsfk.exception;

import com.example.wselsfk.dto.ReturnResult;

/**
 * Created by ZhaoLai Huang on 2018/4/13.
 */
public class UserException extends RuntimeException {
    private ReturnResult.CodeMessage  codeMessage = ReturnResult.CodeMessage.USER_EXCEPTION;

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReturnResult.CodeMessage getCodeMessage() {
        return codeMessage;
    }

    public void setCodeMessage(ReturnResult.CodeMessage codeMessage) {
        this.codeMessage = codeMessage;
    }
}
