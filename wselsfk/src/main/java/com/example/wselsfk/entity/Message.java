package com.example.wselsfk.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by ZhaoLai Huang on 2018/4/10.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Message {
    /**
     * id
     */
    private String id;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息码，系统消息用得比较多
     */
    private String code;

    /**
     * 消息内容
     */
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (msgType != null ? !msgType.equals(message.msgType) : message.msgType != null) return false;
        if (code != null ? !code.equals(message.code) : message.code != null) return false;
        return content != null ? content.equals(message.content) : message.content == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (msgType != null ? msgType.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", msgType='" + msgType + '\'' +
                ", code='" + code + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
