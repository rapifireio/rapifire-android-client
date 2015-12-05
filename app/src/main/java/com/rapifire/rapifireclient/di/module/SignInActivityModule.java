package com.rapifire.rapifireclient.di.module;


import com.rapifire.rapifireclient.data.repository.UserDataRepository;
import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.di.UserComponentBuilder;
import com.rapifire.rapifireclient.domain.interactor.SignInUseCase;
import com.rapifire.rapifireclient.domain.repository.UserRepository;
import com.rapifire.rapifireclient.mvp.presenter.SignInPresenter;
import com.rapifire.rapifireclient.view.activity.SignInActivity;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;
import rx.Scheduler;

/**
 * Created by ktomek on 05.12.15.
 */
@Module
public class SignInActivityModule {

    private SignInActivity signinActivity;

    public SignInActivityModule(final SignInActivity signinActivity) {
        this.signinActivity = signinActivity;
    }

    @Provides
    @ActivityScope
    public SignInActivity provideSignInActivity() {
        return signinActivity;
    }

    @Provides
    @ActivityScope
    public SignInPresenter provideSignInPresenter(SignInUseCase useCase,
                                                  UserComponentBuilder userComponentBuilder) {
        return new SignInPresenter(useCase, userComponentBuilder);
    }

    @Provides
    @ActivityScope
    public UserRepository provideUserRepository(Retrofit.Builder builder,
                                                OkHttpClient okHttpClient) {
        return new UserDataRepository(builder, okHttpClient);
    }

    @Provides
    @ActivityScope
    public SignInUseCase provideSignInUseCase(@Named("postWorkScheduler") Scheduler postScheduler,
                                              @Named("workerScheduler") Scheduler workerSheduler,
                                              UserRepository userRepository) {
        return new SignInUseCase(workerSheduler, postScheduler, userRepository);
    }
}
