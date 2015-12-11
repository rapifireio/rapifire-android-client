package com.rapifire.rapifireclient.mvp.presenter;


import android.util.Log;

import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.domain.interactor.GetThingDetailsUseCase;
import com.rapifire.rapifireclient.domain.interactor.GetThingsUseCase;
import com.rapifire.rapifireclient.domain.interactor.RefreshThingDetailsUseCase;
import com.rapifire.rapifireclient.domain.interactor.RefreshThingsUseCase;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.mvp.view.ThingsView;
import com.rapifire.rapifireclient.mvp.view.ThingDetailsView;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

@ActivityScope
public class ThingDetailsPresenter implements Presenter<ThingDetailsView> {

    private final GetThingDetailsUseCase getThingDetailsUseCase;
    private final RefreshThingDetailsUseCase refreshThingDetailsUseCase;
    private ThingDetailsView view;

    @Inject
    public ThingDetailsPresenter(GetThingDetailsUseCase getThingDetailsUseCase, RefreshThingDetailsUseCase refreshThingDetailsUseCase) {
        this.getThingDetailsUseCase = getThingDetailsUseCase;
        this.refreshThingDetailsUseCase = refreshThingDetailsUseCase;
    }

    public void loadThingDetails(ThingModel thingModel) {
        view.showProgress(true);
        getThingDetailsUseCase.execute(new ThingDetailsSubscriber(), thingModel);
    }

    public void refreshThingDetails(ThingModel thingModel) {
        view.showRefresh(true);
        refreshThingDetailsUseCase.execute(new ThingDetailsSubscriber(), thingModel);
    }

    @Override
    public void subscribe(ThingDetailsView view) {
        this.view = view;
    }

    @Override
    public void unsubscribe(ThingDetailsView view) {
        if (this.view == view) {
            this.view = null;
            refreshThingDetailsUseCase.unsubscribe();
            getThingDetailsUseCase.unsubscribe();
        }
    }

    private class ThingDetailsSubscriber extends Subscriber<ThingDetailsModel> {

        @Override
        public void onCompleted() {
            view.showProgress(false);
            view.showRefresh(false);
        }

        @Override
        public void onError(Throwable throwable) {
            Log.e("ThingDetailsPresenter", throwable.getMessage(), throwable);
            view.showProgress(false);
            view.showRefresh(false);
        }

        @Override
        public void onNext(ThingDetailsModel thingDetails) {
            if (view != null) {
                view.setThingDetails(thingDetails);
            }
        }
    }
}
