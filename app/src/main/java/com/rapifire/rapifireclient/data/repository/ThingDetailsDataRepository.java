package com.rapifire.rapifireclient.data.repository;

import com.rapifire.rapifireclient.data.ThingDetails;
import com.rapifire.rapifireclient.data.cache.MemoryCache;
import com.rapifire.rapifireclient.data.mapper.SimpleModelDataMapper;
import com.rapifire.rapifireclient.data.network.ThingsService;
import com.rapifire.rapifireclient.di.UserScope;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.repository.ThingDetailsRepository;

import javax.inject.Inject;

import rx.Observable;

@UserScope
public class ThingDetailsDataRepository implements ThingDetailsRepository {

    private final MemoryCache memoryCache;
    private final ThingsService thingsService;
    private final SimpleModelDataMapper<ThingDetailsModel, ThingDetails> modelDataMapper;

    @Inject
    public ThingDetailsDataRepository(MemoryCache memoryCache,
                                      ThingsService tweetService,
                                      SimpleModelDataMapper<ThingDetailsModel, ThingDetails> modelDataMapper) {
        this.memoryCache = memoryCache;
        this.thingsService = tweetService;
        this.modelDataMapper = modelDataMapper;
    }

    public Observable<ThingDetailsModel> getThingDetails(ThingModel thing, boolean forceSync) {
        Observable<ThingDetailsModel> networkObservable = thingsService
                .getThingDetails(thing.thingId)
                .flatMap(modelDataMapper);

        return networkObservable;
    }
}
