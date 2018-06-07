package com.example.wselsfk.constant;

/**
 * Created by ZhaoLai Huang on 2018/4/10.
 */
public enum SystemConstant {
    SEARCH_TYPE_USERID("userId"),
    SEARCH_TYPE_USERNAME("userName"),
    SEARCH_TYPE_GAMEROOMID("RoomId"),

    MSG_TYPE_SYSTEM("system"),
    RIVAL_OUTLINE("对方已掉线"),
    CONNECT_EXCEPTION("连接异常"),
    PARAME_ERROR("参数错误");


    private String systemConstant;

    SystemConstant(String systemConstant) {
        this.systemConstant = systemConstant;
    }

    public String getSystemConstant() {
        return systemConstant;
    }

    public void setSystemConstant(String systemConstant) {
        this.systemConstant = systemConstant;
    }

    @Override
    public String toString() {
        return "SystemConstant{" +
                "systemConstant='" + systemConstant + '\'' +
                '}';
    }
}
