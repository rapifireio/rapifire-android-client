package com.rapifire.rapifireclient.domain.interactor;

import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.repository.ThingDetailsRepository;
import com.rapifire.rapifireclient.domain.repository.ThingsRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class RefreshThingDetailsUseCase extends GetUseCase<ThingDetailsModel> {

    private final ThingDetailsRepository repository;

    @Inject
    public RefreshThingDetailsUseCase(@Named("workerSheduler") Scheduler workerSheduler,
                                      @Named("postWorkSheduler") Scheduler postWorkSheduler,
                                      ThingDetailsRepository repository) {
        super(workerSheduler, postWorkSheduler);
        this.repository = repository;
    }

    @Override
    protected Observable<ThingDetailsModel> buildUseCaseObservable() {
        return repository.getThingDetails(true);
    }
}
