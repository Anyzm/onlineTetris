package com.example.wselsfk.exception;

import com.example.wselsfk.dto.ReturnResult;

/**
 * Created by ZhaoLai Huang on 2018/4/13.
 */
public class ConnectException extends UserException {

    private ReturnResult.CodeMessage  codeMessage = ReturnResult.CodeMessage.CONNECTION_EXCEPTION;

    public ConnectException(String message) {
        super(message);
    }

    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReturnResult.CodeMessage getCodeMessage() {
        return codeMessage;
    }

    public void setCodeMessage(ReturnResult.CodeMessage codeMessage) {
        this.codeMessage = codeMessage;
    }
}
