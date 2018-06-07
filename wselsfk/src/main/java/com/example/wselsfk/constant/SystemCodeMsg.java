package com.example.wselsfk.constant;

/**
 * create by Anyzm on 2018/4/15
 */
public enum SystemCodeMsg {
    START_GAME("1001","开始游戏"),
    RIVAL_OUTLINE("9999","对方已掉线");

    private String code;

    private String msg;

    SystemCodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "SystemCodeMsg{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
