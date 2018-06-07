package com.example.wselsfk.constant;

/**
 * Created by ZhaoLai on 16/11/28.
 */

/**
 * session自定义异常码，必须大于2999，小于5000
 */
public enum SessionResult {

    ERROR_PARAME(4000,"参数异常"),
    REPEAT_LOGIN(4001,"其它地方登陆"),
    TOO_FREQUENT(4002,"游戏时间过长"),
    INNER_ERROR(4999,"系统异常");


    private int code;
    private String reason;

    SessionResult(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }


    public String getReason() {
        return reason;
    }


    public static SessionResult codeOf(int index)
    {
        for (SessionResult code : values())
        {
            if (code.getCode()==index)
            {
                return code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "SessionResult{" +
                "code=" + code +
                ", reason='" + reason + '\'' +
                '}';
    }
}
