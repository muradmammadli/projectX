package com.example.statusfilter;

import java.util.Date;

public class Users {

    private String userName;
    private int statusCode;
    private long timeStamp;

    public Users(String userName, int statusCode, long timeStamp) {
        this.userName = userName;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
