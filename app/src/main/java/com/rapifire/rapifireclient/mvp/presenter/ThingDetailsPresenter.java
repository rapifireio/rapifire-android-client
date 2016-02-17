package com.rapifire.rapifireclient.mvp.presenter;


import android.util.Log;

import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.domain.interactor.GetProductCommandsUseCase;
import com.rapifire.rapifireclient.domain.interactor.GetThingDetailsUseCase;
import com.rapifire.rapifireclient.domain.interactor.RefreshThingDetailsUseCase;
import com.rapifire.rapifireclient.domain.model.LatestTimeSeriesModel;
import com.rapifire.rapifireclient.domain.model.ProductCommandModel;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.mvp.view.ThingDetailsView;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

@ActivityScope
public class ThingDetailsPresenter implements Presenter<ThingDetailsView> {

    private final GetThingDetailsUseCase getThingDetailsUseCase;
    private final GetProductCommandsUseCase getProductCommandsUseCase;
    private final RefreshThingDetailsUseCase refreshThingDetailsUseCase;
    private ThingDetailsView view;

    @Inject
    public ThingDetailsPresenter(GetThingDetailsUseCase getThingDetailsUseCase,
                                 RefreshThingDetailsUseCase refreshThingDetailsUseCase, GetProductCommandsUseCase getProductCommandsUseCase) {
        this.getThingDetailsUseCase = getThingDetailsUseCase;
        this.refreshThingDetailsUseCase = refreshThingDetailsUseCase;
        this.getProductCommandsUseCase = getProductCommandsUseCase;
    }

    public void loadThingDetails(ThingModel thingModel) {
        view.showProgress(true);
        getThingDetailsUseCase.execute(new ThingDetailsSubscriber(), thingModel);
    }

    public void refreshThingDetails(ThingModel thingModel) {
        view.showRefresh(true);
        refreshThingDetailsUseCase.execute(new ThingDetailsSubscriber(), thingModel);
    }

    public void onThingLatestDataItemClicked(LatestTimeSeriesModel latestTimeSeriesModel) {
        view.navigateToTimeSeries(latestTimeSeriesModel.getName());
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
            getProductCommandsUseCase.unsubscribe();
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

                getProductCommandsUseCase.execute(new ProductCommandsSubscriber(), thingDetails.getProductModel());
            }
        }
    }

    private class ProductCommandsSubscriber extends Subscriber<List<ProductCommandModel>> {

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
        public void onNext(List<ProductCommandModel> productCommands) {
            if (view != null) {
                view.setProductCommands(productCommands);
            }
        }
    }
}
