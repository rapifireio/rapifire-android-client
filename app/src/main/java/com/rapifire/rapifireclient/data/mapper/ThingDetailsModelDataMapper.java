package com.rapifire.rapifireclient.data.mapper;

import com.rapifire.rapifireclient.data.Thing;
import com.rapifire.rapifireclient.data.ThingDetails;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class ThingDetailsModelDataMapper implements SimpleModelDataMapper<ThingDetailsModel, ThingDetails> {

    @Override
    public ThingDetailsModel transform(ThingDetails thingDetails) {
        return transformThingDetails(thingDetails);
    }

    @Override
    public Observable<ThingDetailsModel> call(ThingDetails thingDetails) {
        return Observable.just(transform(thingDetails));
    }

    private ThingDetailsModel transformThingDetails(ThingDetails thingDetails) {
        final String id = thingDetails.getThingId();
        final String name = thingDetails.getName();
        final ThingModel thingModel = new ThingModel(id, name);

        final ThingDetailsModel thingDetailsModel = new ThingDetailsModel(thingModel);
        return thingDetailsModel;
    }
}
