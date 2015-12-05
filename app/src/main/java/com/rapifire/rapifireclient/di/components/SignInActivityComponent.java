package com.rapifire.rapifireclient.di.components;

import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.di.module.SignInActivityModule;
import com.rapifire.rapifireclient.view.activity.SignInActivity;

import dagger.Subcomponent;

/**
 * Created by ktomek on 05.12.15.
 */
@ActivityScope
@Subcomponent(
        modules = SignInActivityModule.class
)
public interface SignInActivityComponent {

    SignInActivity inject(SignInActivity signinActivity);
}
