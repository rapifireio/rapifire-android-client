package com.rapifire.rapifireclient.mvp.presenter;

import com.rapifire.rapifireclient.data.network.RapifireSession;
import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.di.UserComponentBuilder;
import com.rapifire.rapifireclient.domain.interactor.SignInUseCase;
import com.rapifire.rapifireclient.domain.model.User;
import com.rapifire.rapifireclient.mvp.view.SigninView;

import javax.inject.Inject;

import retrofit.HttpException;
import rx.Subscriber;

/**
 * Created by ktomek on 05.12.15.
 */
@ActivityScope
public class SignInPresenter implements Presenter<SigninView> {

    private final SignInUseCase signInUseCase;
    private final UserComponentBuilder userComponentBuilder;
    private SigninView view;

    @Inject
    public SignInPresenter(SignInUseCase getThingsUseCase,
                           UserComponentBuilder userComponentBuilder) {
        this.signInUseCase = getThingsUseCase;
        this.userComponentBuilder = userComponentBuilder;
    }

    public void signin() {
        view.signInStarted();
        final String username = view.getUsername();
        final String password = view.getPassword();
        signInUseCase.execute(new ThingsSubscriber(), username, password);
    }

    @Override
    public void subscribe(SigninView view) {
        this.view = view;
    }

    @Override
    public void unsubscribe(SigninView view) {
        if (this.view == view) {
            this.view = null;
            signInUseCase.unsubscribe();
        }
    }

    private class ThingsSubscriber extends Subscriber<User> {

        @Override
        public void onCompleted() {
            view.signInFinishedSuccess();
            final RapifireSession rapifireSession = new RapifireSession(view.getUsername(),
                    view.getPassword());
            userComponentBuilder.createUserComponent(rapifireSession);
            view.navigateToThings();
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();

            if(throwable instanceof retrofit.HttpException) {
                HttpException httpException = (HttpException)throwable;
                if(httpException.code() == 401) {
                    view.signInFinishedBadCredentials();

                    return;
                }
            }

            view.showMessage(throwable.getMessage());
        }

        @Override
        public void onNext(User user) {

        }
    }
}
