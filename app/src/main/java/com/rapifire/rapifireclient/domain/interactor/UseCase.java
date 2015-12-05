package com.rapifire.rapifireclient.domain.interactor;

import rx.Scheduler;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by ktomek on 05.12.15.
 */
public abstract class UseCase<T> {

    protected final Scheduler workerSheduler;
    protected final Scheduler postWorkSheduler;

    protected Subscription subscription = Subscriptions.empty();

    public UseCase(Scheduler workerSheduler, Scheduler postWorkSheduler) {
        this.workerSheduler = workerSheduler;
        this.postWorkSheduler = postWorkSheduler;
    }

    public void unsubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }


}
