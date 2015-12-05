package com.rapifire.rapifireclient.domain.repository;

import com.rapifire.rapifireclient.domain.model.User;

import rx.Observable;


/**
 * Created by ktomek on 05.12.15.
 */
public interface UserRepository {
    Observable<User> signInUser(final String username, final String password);
    Observable<User> getUser();
}
