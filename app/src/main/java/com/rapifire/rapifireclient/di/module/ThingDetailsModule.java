package com.rapifire.rapifireclient.di.module;

import com.rapifire.rapifireclient.data.repository.ProductCommandsDataRepository;
import com.rapifire.rapifireclient.data.repository.ThingDetailsDataRepository;
import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.domain.interactor.GetProductCommandsUseCase;
import com.rapifire.rapifireclient.domain.interactor.GetThingDetailsUseCase;
import com.rapifire.rapifireclient.domain.interactor.RefreshThingDetailsUseCase;
import com.rapifire.rapifireclient.view.activity.ThingDetailsActivity;
import com.rapifire.rapifireclient.view.adapter.ThingCommandsAdapter;
import com.rapifire.rapifireclient.view.adapter.ThingLatestDataAdapter;
import com.rapifire.rapifireclient.view.adapter.ThingsAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

@Module
public class ThingDetailsModule {

    private ThingDetailsActivity thingDetailsActivity;

    public ThingDetailsModule(ThingDetailsActivity thingDetailsActivity) {
        this.thingDetailsActivity = thingDetailsActivity;
    }

    @Provides
    @ActivityScope
    public ThingDetailsActivity provideTimelineActivity() {
        return thingDetailsActivity;
    }

    @Provides
    @ActivityScope
    public ThingLatestDataAdapter provideThingLatestDataAdapter() {
        return new ThingLatestDataAdapter(thingDetailsActivity);
    }

    @Provides
    @ActivityScope
    public ThingCommandsAdapter provideThingCommandsAdapter() {
        return new ThingCommandsAdapter(thingDetailsActivity);
    }

    @Provides
    @ActivityScope
    public GetThingDetailsUseCase provideGetThingDetailsUseCase(
            @Named("postWorkScheduler") Scheduler postScheduler,
            @Named("workerScheduler") Scheduler workerSheduler,
            ThingDetailsDataRepository thingsRepository) {
        return new GetThingDetailsUseCase(workerSheduler, postScheduler, thingsRepository);
    }

    @Provides
    @ActivityScope
    public RefreshThingDetailsUseCase provideRefGetThingsUseCase(
            @Named("postWorkScheduler") Scheduler postScheduler,
            @Named("workerScheduler") Scheduler workerSheduler,
            ThingDetailsDataRepository thingsRepository) {
        return new RefreshThingDetailsUseCase(workerSheduler, postScheduler, thingsRepository);
    }

 /*   @Provides
    @ActivityScope
    public GetProductCommandsUseCase provideGetProductCommandsUseCase(
            @Named("postWorkScheduler") Scheduler postScheduler,
            @Named("workerScheduler") Scheduler workerSheduler,
            ProductCommandsDataRepository repository) {
        return new GetProductCommandsUseCase(workerSheduler, postScheduler, repository);
    }*/
}
