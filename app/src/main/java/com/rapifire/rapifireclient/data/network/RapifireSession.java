package com.rapifire.rapifireclient.data.network;

/**
 * Created by ktomek on 04.12.15.
 */
public class RapifireSession {
    public final String username;
    public final String password;

    public RapifireSession(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
