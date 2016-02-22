package com.rapifire.rapifireclient.data.repository;


import com.rapifire.rapifireclient.data.Thing;
import com.rapifire.rapifireclient.data.ThingDetails;
import com.rapifire.rapifireclient.data.cache.MemoryCache;
import com.rapifire.rapifireclient.data.mapper.ModelDataMapper;
import com.rapifire.rapifireclient.data.network.ThingsService;
import com.rapifire.rapifireclient.di.UserScope;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.repository.ThingsRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import com.rapifire.rapifireclient.data.mapper.ThingDetailsModelDataMapper;

/**
 * Created by ktomek on 05.12.15.
 */
@UserScope
public class ThingsDataRepository implements ThingsRepository {

    private final MemoryCache memoryCache;
    private final ThingsService thingsService;
    private final ModelDataMapper<ThingModel, Thing> modelDataMapper;

    @Inject
    public ThingsDataRepository(MemoryCache memoryCache,
                                ThingsService tweetService,
                                ModelDataMapper<ThingModel, Thing> modelDataMapper) {
        this.memoryCache = memoryCache;
        this.thingsService = tweetService;
        this.modelDataMapper = modelDataMapper;
    }

    public Observable<List<ThingModel>> getThings(boolean forceSync) {
        Observable<List<ThingModel>> networkObservable = thingsService
                .getThings()
                .flatMap(modelDataMapper)
                .doOnNext(thingModels -> memoryCache.saveThings(thingModels));
        if (forceSync) {
            return networkObservable;
        }
        Observable<List<ThingModel>> cacheObservable = memoryCache.getThings();
        return Observable.concat(cacheObservable, networkObservable).first();
    }

    public Observable<ThingDetailsModel> getThingDetails(boolean forceSync) {
        return Observable.create(subscriber -> {
                    try {
                        if (!subscriber.isUnsubscribed()) {
                            ThingDetailsModel thingDetailsModel = new ThingDetailsModel();
                            subscriber.onNext(thingDetailsModel);
                            subscriber.onCompleted();
                        }
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }
        );
    }

    public Observable<Void> sendCommandToThing(String thingId, String commandName) {
        return thingsService.sendCommand(thingId, commandName);
    }
}
