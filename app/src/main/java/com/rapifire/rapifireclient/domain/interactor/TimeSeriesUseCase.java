package com.rapifire.rapifireclient.domain.interactor;

import com.rapifire.rapifireclient.domain.model.ChartItemModel;
import com.rapifire.rapifireclient.domain.model.ChartModel;
import com.rapifire.rapifireclient.domain.model.TimeSeriesModel;
import com.rapifire.rapifireclient.domain.repository.TimeSeriesRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by ktomek on 08.12.15.
 */
public class TimeSeriesUseCase extends UseCase<List<ChartItemModel>> {

    private static final long LAST_MILLIS = 450000;
    private final TimeSeriesRepository timeSeriesRepository;
    private final String thingId;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

    private Subscription timeSeriesTypeSubscription = Subscriptions.empty();


    @Inject
    public TimeSeriesUseCase(@Named("workerScheduler") Scheduler workerScheduler,
                             @Named("postWorkScheduler") Scheduler postWorkScheduler,
                             TimeSeriesRepository timeSeriesRepository, final String thingId) {
        super(workerScheduler, postWorkScheduler);
        this.timeSeriesRepository = timeSeriesRepository;
        this.thingId = thingId;
    }

    public void execute(Subscriber timeSeriesSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(workerSheduler)
                .observeOn(postWorkSheduler)
                .subscribe(timeSeriesSubscriber);
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
        if (!timeSeriesTypeSubscription.isUnsubscribed()) {
            timeSeriesTypeSubscription.unsubscribe();
        }
    }


    private Observable<ChartModel> buildUseCaseObservable() {
        Observable<TimeSeriesModel> cache = timeSeriesRepository
                .getTimeSeries(thingId, LAST_MILLIS)
                .flatMap(timeSeriesModels -> Observable.from(timeSeriesModels))
                .toSortedList((timeSeriesModel, timeSeriesModel2) -> {
                            final long lhs = timeSeriesModel.getDataTimeMillis();
                            final long rhs = timeSeriesModel2.getDataTimeMillis();
                            return lhs < rhs ? -1 : (lhs == rhs ? 0 : 1);
                        }
                ).flatMap(timeSeriesModels -> Observable.from(timeSeriesModels))
                .cache();
        Observable<ChartModel> doubleObservable = buildDoubleUseCaseObservable(cache);
        Observable<ChartModel> stringObservable = buildStringUseCaseObservable(cache);
        return Observable.concat(doubleObservable, stringObservable).filter(chartModel
                -> chartModel.getItems().size() > 0);
    }

    private Observable<ChartModel> buildDoubleUseCaseObservable(Observable<TimeSeriesModel> cache) {
        return cache
                .filter(timeSeriesModel -> timeSeriesModel.getDoubleValue() != null)
                .map(timeSeriesModel -> {
                            final String label = simpleDateFormat.format(
                                    new Date(timeSeriesModel.getDataTimeMillis()));
                            return new ChartItemModel(label,
                                    timeSeriesModel.getDoubleValue());
                        }
                )
                .toList()
                .map(chartItemModels -> new ChartModel(TimeSeriesType.DOUBLE, chartItemModels));
    }

    private Observable<ChartModel> buildStringUseCaseObservable(Observable<TimeSeriesModel> cache) {
        return cache
                .filter(timeSeriesModel -> timeSeriesModel.getStringValue() != null)
                .map(timeSeriesModel -> timeSeriesModel.getStringValue())
                .groupBy(String::toString)
                .flatMap(stringStringGroupedObservable ->
                        Observable.zip(
                                Observable.just(stringStringGroupedObservable.getKey()),
                                stringStringGroupedObservable.count(),
                                (label, count) -> new ChartItemModel(label, count))
                ).toList()
                .map(chartItemModels -> new ChartModel(TimeSeriesType.STRING, chartItemModels));
    }
}
