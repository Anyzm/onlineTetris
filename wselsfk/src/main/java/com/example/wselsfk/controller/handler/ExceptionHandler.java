package com.example.wselsfk.controller.handler;

import com.example.wselsfk.dto.ReturnResult;
import com.example.wselsfk.exception.GameException;
import com.example.wselsfk.exception.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ZhaoLai Huang on 2018/4/13.
 */

@ControllerAdvice
public class ExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ReturnResult handle(Exception e) {
        if (e instanceof GameException) {
            GameException gameException = (GameException) e;
            return new ReturnResult(gameException.getCodeMessage());
        }if(e instanceof UserException){
            UserException userException = (UserException) e;
            return new ReturnResult(userException.getCodeMessage());
        }else {
            logger.error("【系统异常】{}", e);
            return new ReturnResult(ReturnResult.CodeMessage.INNER_ERROR);
        }
    }



}
