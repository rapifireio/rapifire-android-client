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

public class GetThingDetailsUseCase extends GetUseCase<ThingDetailsModel> {

    private final ThingDetailsRepository repository;

    @Inject
    public GetThingDetailsUseCase(@Named("workerScheduler") Scheduler workerSheduler,
                                  @Named("postWorkScheduler") Scheduler postWorkSheduler,
                                  ThingDetailsRepository repository) {
        super(workerSheduler, postWorkSheduler);
        this.repository = repository;
    }

    @Override
    protected Observable<ThingDetailsModel> buildUseCaseObservable() {
        return repository.getThingDetails(false);
    }
}
