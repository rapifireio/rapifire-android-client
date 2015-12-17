package com.rapifire.rapifireclient.data.network;

import com.rapifire.rapifireclient.domain.model.User;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by ktomek on 05.12.15.
 */
public interface UserService {

    @GET("/api/v1/myself")
    Observable<User> getUser();
}
