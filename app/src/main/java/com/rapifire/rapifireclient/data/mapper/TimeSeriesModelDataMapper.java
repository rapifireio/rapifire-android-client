package com.rapifire.rapifireclient.data.mapper;

import com.rapifire.rapifireclient.data.TimeSeries;
import com.rapifire.rapifireclient.di.UserScope;
import com.rapifire.rapifireclient.domain.model.TimeSeriesModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by ktomek on 08.12.15.
 */
@UserScope
public class TimeSeriesModelDataMapper implements ModelDataMapper<TimeSeriesModel, TimeSeries> {

    @Inject
    public TimeSeriesModelDataMapper() {

    }

    @Override
    public TimeSeriesModel transform(TimeSeries timeSeries) {
        final long dataTimeMillis = timeSeries.getDataTimeMillis();
        final Double doubleValue = timeSeries.getDoubleValue();
        final String stringValue = timeSeries.getStringValue();
        final TimeSeriesModel timeSeriesModel = new TimeSeriesModel(dataTimeMillis, doubleValue, stringValue);
        return timeSeriesModel;
    }

    @Override
    public List<TimeSeriesModel> transform(List<TimeSeries> collection) {
        final int size = collection.size();
        final List<TimeSeriesModel> timeSeriesModels = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            final TimeSeries timeSeries = collection.get(i);
            timeSeriesModels.add(transform(timeSeries));
        }
        return timeSeriesModels;
    }

    @Override
    public Observable<List<TimeSeriesModel>> call(List<TimeSeries> timeSeries) {
        return Observable.just(transform(timeSeries));
    }
}
