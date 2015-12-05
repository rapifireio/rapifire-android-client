package com.rapifire.rapifireclient.di.module;

import android.app.Application;

import com.rapifire.rapifireclient.RapidfireApp;
import com.rapifire.rapifireclient.di.UserComponentBuilder;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ktomek on 05.12.15.
 */
@Module
public class AppModule {

    //change it to final field and pass by constructor
    public static final String BASE_URL = "http://rapifire.com/api/v1/";

    private final RapidfireApp application;

    public AppModule(RapidfireApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public UserComponentBuilder provideUserComponentBuilder() {
        return application;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    public Retrofit.Builder provideBuilder(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
//                .addConverterFactory(MoshiConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL);
    }

    @Provides
    @Named("postWorkScheduler")
    @Singleton
    public Scheduler provideViewScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named("workerScheduler")
    @Singleton
    public Scheduler provideWorkerScheduler() {
        return Schedulers.io();
    }
}
