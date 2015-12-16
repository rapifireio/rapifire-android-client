package com.rapifire.rapifireclient.domain.interactor;

import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.repository.ThingDetailsRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

public class GetThingDetailsUseCase extends UseCase<ThingDetailsModel> {

    private final ThingDetailsRepository repository;

    @Inject
    public GetThingDetailsUseCase(@Named("workerScheduler") Scheduler workerSheduler,
                                  @Named("postWorkScheduler") Scheduler postWorkSheduler,
                                  ThingDetailsRepository repository) {
        super(workerSheduler, postWorkSheduler);
        this.repository = repository;
    }

    public void execute(final Subscriber UseCaseSubscriber, ThingModel thing) {
        this.subscription = this.buildUseCaseObservable(thing)
                .subscribeOn(workerSheduler)
                .observeOn(postWorkSheduler)
                .subscribe(UseCaseSubscriber);
    }

    protected Observable<ThingDetailsModel> buildUseCaseObservable(ThingModel thing) {
        return repository.getThingDetails(thing, false);
    }
}
