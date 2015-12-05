package com.rapifire.rapifireclient.domain.interactor;

import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.repository.ThingsRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by ktomek on 05.12.15.
 */
public class RefreshThingsUseCase extends GetUseCase<List<ThingModel>> {

    private final ThingsRepository repository;

    @Inject
    public RefreshThingsUseCase(@Named("workerSheduler") Scheduler workerSheduler,
                                @Named("postWorkSheduler") Scheduler postWorkSheduler,
                                ThingsRepository repository) {
        super(workerSheduler, postWorkSheduler);
        this.repository = repository;
    }

    @Override
    protected Observable<List<ThingModel>> buildUseCaseObservable() {
        return repository.getThings(true);
    }
}
