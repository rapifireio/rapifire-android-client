package com.rapifire.rapifireclient.domain.repository;

import com.rapifire.rapifireclient.domain.model.TimeSeriesModel;

import java.util.List;

import rx.Observable;

/**
 * Created by ktomek on 08.12.15.
 */
public interface TimeSeriesRepository {

    Observable<List<TimeSeriesModel>> getTimeSeries(String thingId, String key, long lastMillis);
}
