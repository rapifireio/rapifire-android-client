package com.rapifire.rapifireclient.domain.interactor;

import com.rapifire.rapifireclient.domain.model.User;
import com.rapifire.rapifireclient.domain.repository.ThingDetailsRepository;
import com.rapifire.rapifireclient.domain.repository.ThingsRepository;
import com.rapifire.rapifireclient.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by ktomek on 05.12.15.
 */
public class SendCommandToThingUseCase extends UseCase<Void> {

    private final ThingsRepository repository;

    @Inject
    public SendCommandToThingUseCase(@Named("workerScheduler") Scheduler workerSheduler,
                                     @Named("postWorkScheduler") Scheduler postWorkSheduler,
                                     ThingsRepository repository) {
        super(workerSheduler, postWorkSheduler);
        this.repository = repository;

    }

    protected Observable<Void> buildUseCaseObservable(final String thingId,
                                                      final String commandName) {
        return repository.sendCommandToThing(thingId, commandName);
    }

    public void execute(final Subscriber useCaseSubscriber,
                        final String thingId,
                        final String commandName) {
        this.subscription = this.buildUseCaseObservable(thingId, commandName)
                .subscribeOn(workerSheduler)
                .observeOn(postWorkSheduler)
                .subscribe(useCaseSubscriber);
    }
}
