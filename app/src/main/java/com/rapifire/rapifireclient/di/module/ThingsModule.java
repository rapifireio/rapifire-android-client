package com.rapifire.rapifireclient.di.module;

import com.rapifire.rapifireclient.data.repository.ThingsDataRepository;
import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.domain.interactor.GetThingsUseCase;
import com.rapifire.rapifireclient.domain.interactor.RefreshThingsUseCase;
import com.rapifire.rapifireclient.mvp.presenter.ThingsPresenter;
import com.rapifire.rapifireclient.view.activity.ThingsActivity;
import com.rapifire.rapifireclient.view.adapter.ThingsAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by ktomek on 05.12.15.
 */
@Module
public class ThingsModule {

    private ThingsActivity thingsActivity;

    public ThingsModule(ThingsActivity thingsActivity) {
        this.thingsActivity = thingsActivity;
    }

    @Provides
    @ActivityScope
    public ThingsActivity provideTimelineActivity() {
        return thingsActivity;
    }


    @Provides
    @ActivityScope
    public ThingsAdapter provideThingsAdapter() {
        return new ThingsAdapter(thingsActivity);
    }

    @Provides
    @ActivityScope
    public GetThingsUseCase provideGetThingsUseCase(@Named("postWorkScheduler") Scheduler postScheduler,
                                                    @Named("workerScheduler") Scheduler workerSheduler,
                                                    ThingsDataRepository thingsRepository) {
        return new GetThingsUseCase(workerSheduler,postScheduler,  thingsRepository);
    }

    @Provides
    @ActivityScope
    public RefreshThingsUseCase provideRefGetThingsUseCase(@Named("postWorkScheduler") Scheduler postScheduler,
                                                           @Named("workerScheduler") Scheduler workerSheduler,
                                                           ThingsDataRepository thingsRepository) {
        return new RefreshThingsUseCase(workerSheduler,postScheduler, thingsRepository);
    }
}
