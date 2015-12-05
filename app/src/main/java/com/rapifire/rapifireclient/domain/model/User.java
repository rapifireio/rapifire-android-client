package com.rapifire.rapifireclient.domain.model;

/**
 * Created by ktomek on 05.12.15.
 */
public class User {
    private String username;
    private String authId;
    private String authKey;
    private String password;

    public User(String username, String authId, String authKey, String password) {
        this.username = username;
        this.authId = authId;
        this.authKey = authKey;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthKey() {
        return authKey;
    }

    public String getAuthId() {
        return authId;
    }

    public String getUsername() {
        return username;
    }
}
