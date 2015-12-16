package com.rapifire.rapifireclient.di.module;

import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.domain.interactor.TimeSeriesUseCase;
import com.rapifire.rapifireclient.domain.repository.TimeSeriesRepository;
import com.rapifire.rapifireclient.view.activity.TimeSeriesActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by ktomek on 09.12.15.
 */
@Module
public class TimeSeriesModule {

    private final TimeSeriesActivity timeSeriesActivity;
    private final String thingId;

    public TimeSeriesModule(TimeSeriesActivity timeSeriesActivity, final String thingId) {
        this.timeSeriesActivity = timeSeriesActivity;
        this.thingId = thingId;
    }

    @Provides
    @ActivityScope
    public TimeSeriesActivity provideTimeSeriesActivity() {
        return timeSeriesActivity;
    }


    @Provides
    @ActivityScope
    public TimeSeriesUseCase provideTimeSeriesUseCase(@Named("postWorkScheduler") Scheduler postScheduler,
                                                      @Named("workerScheduler") Scheduler workerSheduler,
                                                      TimeSeriesRepository timeSeriesRepository) {
        return new TimeSeriesUseCase(workerSheduler, postScheduler, timeSeriesRepository, thingId);
    }
}
