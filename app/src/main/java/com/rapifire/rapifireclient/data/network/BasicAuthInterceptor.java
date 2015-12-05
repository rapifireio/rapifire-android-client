package com.rapifire.rapifireclient.data.network;

import android.util.Base64;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by ktomek on 04.12.15.
 */
public class BasicAuthInterceptor implements Interceptor {
    private final String basic;


    public BasicAuthInterceptor(final String username, final String password) {
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        } else {
            throw new IllegalArgumentException("Username and password can not be  null");
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder requestBuilder = original.newBuilder()
                .header("Authorization", basic)
                .method(original.method(), original.body());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
