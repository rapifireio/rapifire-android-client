package com.rapifire.rapifireclient.mvp.presenter;


import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.domain.interactor.GetThingsUseCase;
import com.rapifire.rapifireclient.domain.interactor.RefreshThingsUseCase;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.mvp.view.ThingsView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by ktomek on 05.12.15.
 */
@ActivityScope
public class ThingsPresenter implements Presenter<ThingsView> {

    private final GetThingsUseCase getThingsUseCase;
    private final RefreshThingsUseCase refreshThingsUseCase;
    private ThingsView view;

    @Inject
    public ThingsPresenter(GetThingsUseCase getThingsUseCase, RefreshThingsUseCase refreshThingsUseCase) {
        this.getThingsUseCase = getThingsUseCase;
        this.refreshThingsUseCase = refreshThingsUseCase;
    }

    public void loadThings() {
        view.showProgress(true);
        getThingsUseCase.execute(new ThingsSubscriber());
    }

    public void refreshThings() {
        view.showRefresh(true);
        refreshThingsUseCase.execute(new ThingsSubscriber());
    }

    @Override
    public void subscribe(ThingsView view) {
        this.view = view;
    }

    @Override
    public void unsubscribe(ThingsView view) {
        if (this.view == view) {
            this.view = null;
            refreshThingsUseCase.unsubscribe();
            getThingsUseCase.unsubscribe();
        }
    }

    private class ThingsSubscriber extends Subscriber<List<ThingModel>> {

        @Override
        public void onCompleted() {
            view.showProgress(false);
            view.showRefresh(false);
        }

        @Override
        public void onError(Throwable throwable) {
            view.showProgress(false);
            view.showRefresh(false);
        }

        @Override
        public void onNext(List<ThingModel> things) {
            if (view != null) {
                view.setThings(things);
            }
        }
    }
}
