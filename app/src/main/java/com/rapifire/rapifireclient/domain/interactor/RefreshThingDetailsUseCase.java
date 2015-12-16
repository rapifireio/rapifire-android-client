package com.rapifire.rapifireclient.domain.interactor;

import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.repository.ThingDetailsRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

public class RefreshThingDetailsUseCase extends UseCase<ThingDetailsModel> {

    private final ThingDetailsRepository repository;

    @Inject
    public RefreshThingDetailsUseCase(@Named("workerScheduler") Scheduler workerScheduler,
                                      @Named("postWorkScheduler") Scheduler postWorkScheduler,
                                      ThingDetailsRepository repository) {
        super(workerScheduler, postWorkScheduler);
        this.repository = repository;
    }

    public void execute(final Subscriber UseCaseSubscriber, ThingModel thing) {
        this.subscription = this.buildUseCaseObservable(thing)
                .subscribeOn(workerSheduler)
                .observeOn(postWorkSheduler)
                .subscribe(UseCaseSubscriber);
    }

    protected Observable<ThingDetailsModel> buildUseCaseObservable(ThingModel thing) {
        return repository.getThingDetails(thing, true);
    }
}
