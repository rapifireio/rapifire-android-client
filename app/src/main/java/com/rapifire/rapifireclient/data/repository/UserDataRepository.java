package com.rapifire.rapifireclient.data.repository;

import com.rapifire.rapifireclient.domain.model.User;
import com.rapifire.rapifireclient.data.network.BasicAuthInterceptor;
import com.rapifire.rapifireclient.data.network.UserService;
import com.rapifire.rapifireclient.domain.repository.UserRepository;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Inject;

import retrofit.Retrofit;
import rx.Observable;

/**
 * Created by ktomek on 05.12.15.
 */
public class UserDataRepository implements UserRepository {

    private final Retrofit.Builder builder;
    private final OkHttpClient okHttpClient;

    @Inject
    public UserDataRepository(Retrofit.Builder builder, OkHttpClient okHttpClient) {
        this.builder = builder;
        this.okHttpClient = okHttpClient;
    }

    @Override
    public Observable<User> signInUser(String username, String password) {
        final BasicAuthInterceptor interceptor = new BasicAuthInterceptor(username, password);
        okHttpClient.interceptors().add(interceptor);
        final Retrofit retrofit = builder
                .build();
        final UserService userService = retrofit.create(UserService.class);
        return userService.getUser();
    }

    @Override
    public Observable<User> getUser() {
        return null;
    }
}
