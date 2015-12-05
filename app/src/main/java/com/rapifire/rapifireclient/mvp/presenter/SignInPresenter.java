package com.rapifire.rapifireclient.mvp.presenter;

import com.rapifire.rapifireclient.data.network.RapidfireSession;
import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.di.UserComponentBuilder;
import com.rapifire.rapifireclient.domain.interactor.SignInUseCase;
import com.rapifire.rapifireclient.domain.model.User;
import com.rapifire.rapifireclient.mvp.view.SigninView;

import javax.inject.Inject;

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
        view.showProgress(true);
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
            view.showProgress(false);
            final RapidfireSession rapidfireSession = new RapidfireSession(view.getUsername(),
                    view.getPassword());
            userComponentBuilder.createUserComponent(rapidfireSession);
            view.navigateToThings();
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
            view.showProgress(false);
            view.showMessage("Hahahah! Nice try");
        }

        @Override
        public void onNext(User user) {

        }
    }
}
