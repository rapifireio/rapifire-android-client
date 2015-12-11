package com.rapifire.rapifireclient.data.repository;

import com.rapifire.rapifireclient.data.mapper.ModelDataMapper;
import com.rapifire.rapifireclient.data.Thing;
import com.rapifire.rapifireclient.data.ThingDetails;
import com.rapifire.rapifireclient.data.cache.MemoryCache;
import com.rapifire.rapifireclient.data.mapper.SimpleModelDataMapper;
import com.rapifire.rapifireclient.data.mapper.ThingDetailsModelDataMapper;
import com.rapifire.rapifireclient.data.network.ThingsService;
import com.rapifire.rapifireclient.di.UserScope;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.repository.ThingDetailsRepository;
import com.rapifire.rapifireclient.domain.repository.ThingsRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

@UserScope
public class ThingDetailsDataRepository implements ThingDetailsRepository {

    private final MemoryCache memoryCache;
    private final ThingsService thingsService;
    private final SimpleModelDataMapper<ThingDetailsModel, ThingDetails> modelDataMapper;

    private Action1<List<ThingModel>> saveThingsAction = new Action1<List<ThingModel>>() {
        @Override
        public void call(List<ThingModel> tweets) {
            memoryCache.saveThings(tweets);
        }
    };

    @Inject
    public ThingDetailsDataRepository(MemoryCache memoryCache,
                                      ThingsService tweetService,
                                      SimpleModelDataMapper<ThingDetailsModel, ThingDetails> modelDataMapper) {
        this.memoryCache = memoryCache;
        this.thingsService = tweetService;
        this.modelDataMapper = modelDataMapper;
    }

    public Observable<ThingDetailsModel> getThingDetails(ThingModel thing, boolean forceSync) {
        //l1WYo2rTa8YfkiTtusaRMM-UfO4=
        //return thingsService.getThingDetails(thing.thingId);

        Observable<ThingDetailsModel> networkObservable = thingsService
                .getThingDetails(thing.thingId)
                .flatMap(modelDataMapper);

        return networkObservable;
    }
}
