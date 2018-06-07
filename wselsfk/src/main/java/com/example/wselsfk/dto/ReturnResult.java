package com.example.wselsfk.dto;

/**
 * Created by ZhaoLai Huang on 2018/4/10.
 */

/**
 * 返回结果
 */
public class ReturnResult {
    /**
     * 返回状态码
     */
    private String code;

    /**
     * 返回描述
     */
     private String message;

    /**
     * 返回的实际对象，可为空
     */
    private Object body;

    public ReturnResult(CodeMessage codeMessage ,Object body) {
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();
        this.body = body;
    }
    public ReturnResult(CodeMessage codeMessage) {
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReturnResult)) return false;
        ReturnResult that = (ReturnResult) o;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return body != null ? body.equals(that.body) : that.body == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReturnResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public static enum CodeMessage{
        SUCCESS("0","成功"),
        ERROR_PARAME("-1","参数错误"),
        INNER_ERROR("-999","内部错误"),
        NO_LOGIN("-2","没有登陆"),
        CONNECTION_EXCEPTION("-3","连接异常"),
        ROOM_NO_EXSITANT("-4","房间不存在"),
        ROOM_FULL("-5","房间已满"),
        USER_EXCEPTION("-6","用户异常"),
        GAME_EXCEPTION("-7","游戏异常"),
        ROOM_EXIST("-9","房间已存在"),
        CANNOT_JOIN_SELFROOM("-8","不能加入自己房间");

        CodeMessage(String code, String message) {
            this.code = code;
            this.message = message;
        }

        /**
        *   状态码
        */
        private String code;

        /**
         * 状态信息描述
         */
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


        @Override
        public String toString() {
            return "CodeMessage{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}
