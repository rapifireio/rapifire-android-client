package com.rapifire.rapifireclient.di.components;

import com.rapifire.rapifireclient.di.module.AppModule;
import com.rapifire.rapifireclient.di.module.SignInActivityModule;
import com.rapifire.rapifireclient.di.module.UserModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by ktomek on 05.12.15.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    UserComponent plus(UserModule userModule);

    SignInActivityComponent plus(SignInActivityModule signinActivityModule);
}
