package com.rapifire.rapifireclient.domain.interactor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Created by ktomek on 05.12.15.
 */
public abstract class GetUseCase<T> extends UseCase<T> {

    public GetUseCase(Scheduler workerSheduler, Scheduler postWorkSheduler) {
        super(workerSheduler, postWorkSheduler);
    }

    protected abstract Observable<T> buildUseCaseObservable();

    public void execute(Subscriber useCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(workerSheduler)
                .observeOn(postWorkSheduler)
                .subscribe(useCaseSubscriber);
    }
}
