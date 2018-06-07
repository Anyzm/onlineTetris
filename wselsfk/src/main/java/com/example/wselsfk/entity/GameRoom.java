package com.example.wselsfk.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Objects;

/**
 * Created by ZhaoLai Huang on 2018/4/9.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GameRoom {

    /**
     * 房间id
     */
    private String id;
    
    /**
     * 游戏房间创建者
     */
    private User owner;
    
    /**
     * 游戏参与者
     */
    private User joiner;

    /**
     * 房间创建时间
     */
    private String createTime;

    /**
     * 游戏开始时间
     */
    private String startTime;

    /**
     * 游戏结束时间
     */
    private String endTime;

    /**
     * 游戏加入密码
     */
    private String password;

    /**
     * 游戏是否可以被公开查到
     */
    private boolean openToAll;
    
    /**
     * 游戏状态(等待{wait}，游戏中(ing)，结束(end))
     */
    private String gameState;

    /**
     * 获得对手
     * @param userId
     * @return
     */
    public  User getRival(String userId){
        if(owner!=null && Objects.equals(userId,owner.getUserId())){
            return joiner;
        }else if(joiner!=null && Objects.equals(userId,joiner.getUserId()) ){
            return owner;
        }
        return null;
    }



    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getJoiner() {
        return joiner;
    }

    public void setJoiner(User joiner) {
        this.joiner = joiner;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isOpenToAll() {
        return openToAll;
    }

    public void setOpenToAll(boolean openToAll) {
        this.openToAll = openToAll;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameRoom)) return false;
        GameRoom gameRoom = (GameRoom) o;
        if (id != null ? !id.equals(gameRoom.id) : gameRoom.id != null) return false;
        if (owner != null ? !owner.equals(gameRoom.owner) : gameRoom.owner != null) return false;
        if (joiner != null ? !joiner.equals(gameRoom.joiner) : gameRoom.joiner != null) return false;
        return createTime != null ? createTime.equals(gameRoom.createTime) : gameRoom.createTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (joiner != null ? joiner.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GameRoom{" +
                "id='" + id + '\'' +
                ", owner='" + (owner==null?"null":(owner.getUserName())) + '\'' +
                ", joiner='" + (joiner==null?"null":(joiner.getUserName())) + '\'' +
                ", createTime='" + createTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", openToAll=" + openToAll +
                '}';
    }


    public enum GameState{
        WAIT("等待"),
        ING("游戏中"),
        END("结束"),;

        /**
         * 游戏状态
         */
        private String state;

        GameState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return "GameState{" +
                    "state='" + state + '\'' +
                    '}';
        }
    }
}
