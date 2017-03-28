package com.example.lenovo.emeetsc.bean;

/**
 * Created by ydc on 17/3/16.
 */

public class UserInfo {
    private int userId;
    private String token;
    private String authorization;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



}
