package com.rapifire.rapifireclient.data.network;

/**
 * Created by ktomek on 04.12.15.
 */
public class RapidfireSession {
    public final String username;
    public final String password;

    public RapidfireSession(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
