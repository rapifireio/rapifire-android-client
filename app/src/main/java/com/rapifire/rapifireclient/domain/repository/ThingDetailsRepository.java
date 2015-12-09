package com.rapifire.rapifireclient.domain.repository;

import com.rapifire.rapifireclient.data.Thing;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;

import java.util.List;

import rx.Observable;

public interface ThingDetailsRepository {
    Observable<ThingDetailsModel> getThingDetails(boolean forceSync);
}
