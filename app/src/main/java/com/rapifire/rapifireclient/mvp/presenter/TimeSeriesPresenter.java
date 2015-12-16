package com.rapifire.rapifireclient.mvp.presenter;

import com.rapifire.rapifireclient.domain.interactor.TimeSeriesType;
import com.rapifire.rapifireclient.domain.interactor.TimeSeriesUseCase;
import com.rapifire.rapifireclient.domain.model.ChartModel;
import com.rapifire.rapifireclient.mvp.view.TimeSeriesView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by ktomek on 09.12.15.
 */
public class TimeSeriesPresenter implements Presenter<TimeSeriesView> {

    private final TimeSeriesUseCase timeSeriesUseCase;
    private TimeSeriesView view;

    @Inject
    public TimeSeriesPresenter(TimeSeriesUseCase timeSeriesUseCase) {
        this.timeSeriesUseCase = timeSeriesUseCase;
    }

    public void loadTimeSeries() {
        view.showProgress(true);
        timeSeriesUseCase.execute(new TimeSeriesSubscriber());
    }

    @Override
    public void subscribe(TimeSeriesView view) {
        this.view = view;
    }

    @Override
    public void unsubscribe(TimeSeriesView view) {
        if (this.view == view) {
            this.view = null;
            timeSeriesUseCase.unsubscribe();
        }
    }

    private class TimeSeriesSubscriber extends Subscriber<ChartModel> {

        @Override
        public void onCompleted() {
            view.showProgress(false);
        }

        @Override
        public void onError(Throwable throwable) {
            view.showProgress(false);
            view.showMessage("Error while getting data...");
        }

        @Override
        public void onNext(ChartModel chartModel) {
            if (chartModel.getType() == TimeSeriesType.DOUBLE) {
                if (view != null) {
                    view.setLineData(chartModel.getItems());
                }
            } else if (chartModel.getType() == TimeSeriesType.STRING) {
                // set data
                if (view != null) {
                    view.setPieData(chartModel.getItems());
                }
            }
        }
    }
}

