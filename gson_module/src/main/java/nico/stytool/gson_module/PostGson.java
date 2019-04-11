package nico.stytool.gson_module;

import java.util.List;

public class PostGson {


    /**
     * id : 1
     * userId : 100000001
     * dateTime : 2019-04-10 11:56:53
     * postTitle : 测试测试测试测试测试测试
     * topic : #测试测试
     * isPermit : 1
     */

    private int id;
    private List<String> picture;

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    private int userId;
    private String dateTime;
    private String postTitle;
    private String topic;
    private UserGson user;

    public UserGson getUser() {
        return user;
    }

    public void setUser(UserGson user) {
        this.user = user;
    }

    private String isPermit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getIsPermit() {
        return isPermit;
    }

    public void setIsPermit(String isPermit) {
        this.isPermit = isPermit;
    }
}
