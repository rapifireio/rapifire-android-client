package com.rapifire.rapifireclient.data.repository;

import com.rapifire.rapifireclient.data.mapper.TimeSeriesModelDataMapper;
import com.rapifire.rapifireclient.data.network.ThingsService;
import com.rapifire.rapifireclient.domain.model.TimeSeriesModel;
import com.rapifire.rapifireclient.domain.repository.TimeSeriesRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by ktomek on 08.12.15.
 */
public class TimeSeriesDataRepository implements TimeSeriesRepository {

    private final ThingsService thingsService;
    private final TimeSeriesModelDataMapper modelDataMapper;

    public TimeSeriesDataRepository(final ThingsService thingsService,
                                    TimeSeriesModelDataMapper modelDataMapper) {
        this.thingsService = thingsService;
        this.modelDataMapper = modelDataMapper;
    }

    @Override
    public Observable<List<TimeSeriesModel>> getTimeSeries(String thingId, String key, long lastMillis) {
        return thingsService
                .getTimeSeries(thingId, key, lastMillis)
                .flatMap(modelDataMapper);
    }
}
