package com.rapifire.rapifireclient.domain.interactor;

import com.rapifire.rapifireclient.domain.model.User;
import com.rapifire.rapifireclient.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by ktomek on 05.12.15.
 */
public class SignInUseCase extends UseCase<User> {

    private final UserRepository userRepository;

    @Inject
    public SignInUseCase(@Named("workerScheduler") Scheduler workerSheduler,
                         @Named("postWorkScheduler") Scheduler postWorkSheduler,
                         UserRepository userRepository) {
        super(workerSheduler, postWorkSheduler);
        this.userRepository = userRepository;

    }

    protected Observable<User> buildUseCaseObservable(final String username,
                                                      final String password) {
        return userRepository.signInUser(username, password);
    }

    public void execute(final Subscriber useCaseSubscriber,
                        final String username,
                        final String password) {
        this.subscription = this.buildUseCaseObservable(username, password)
                .subscribeOn(workerSheduler)
                .observeOn(postWorkSheduler)
                .subscribe(useCaseSubscriber);
    }
}
