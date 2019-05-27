package com.ian.gamerestapi.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    private String user;
    private String message;
    private long dateCreated;
    private Integer like;

    public Comment() {
    }

    public Comment(String user, String message, long dateCreated, Integer like) {
        this.user = user;
        this.message = message;
        this.dateCreated = dateCreated;
        this.like = like;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateCreated() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date(dateCreated * 1000);

        return format.format(date);
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }
}
