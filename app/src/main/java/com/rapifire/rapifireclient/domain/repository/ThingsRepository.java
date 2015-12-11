package com.rapifire.rapifireclient.domain.repository;

import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;

import java.util.List;

import rx.Observable;

/**
 * Created by ktomek on 05.12.15.
 */
public interface ThingsRepository {
    Observable<List<ThingModel>> getThings(boolean forceSync);
}
