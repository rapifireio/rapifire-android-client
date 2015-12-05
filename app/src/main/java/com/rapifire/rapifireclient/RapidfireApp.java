package com.rapifire.rapifireclient;

import android.app.Application;
import android.content.Context;

import com.rapifire.rapifireclient.data.network.RapidfireSession;
import com.rapifire.rapifireclient.di.UserComponentBuilder;
import com.rapifire.rapifireclient.di.components.AppComponent;
import com.rapifire.rapifireclient.di.components.DaggerAppComponent;
import com.rapifire.rapifireclient.di.components.UserComponent;
import com.rapifire.rapifireclient.di.module.AppModule;
import com.rapifire.rapifireclient.di.module.UserModule;


/**
 * Created by ktomek on 05.12.15.
 */
public class RapidfireApp extends Application implements UserComponentBuilder {


    private AppComponent mAppComponent;
    private UserComponent mUserComponent;

    public static RapidfireApp get(Context context) {
        return (RapidfireApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent();
    }

    private void initAppComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public UserComponent createUserComponent(RapidfireSession session) {
        mUserComponent = mAppComponent.plus(new UserModule(session));
        return mUserComponent;
    }

    public void releaseUserComponent() {
        mUserComponent = null;
    }

    public UserComponent getUserComponent() {
        return mUserComponent;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
