package com.rapifire.rapifireclient.data.repository;


import com.rapifire.rapifireclient.data.Thing;
import com.rapifire.rapifireclient.data.cache.MemoryCache;
import com.rapifire.rapifireclient.data.network.ThingsService;
import com.rapifire.rapifireclient.di.UserScope;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.repository.ThingDetailsRepository;
import com.rapifire.rapifireclient.domain.repository.ThingsRepository;
import com.rapifire.rapifireclient.data.mapper.ModelDataMapper;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by ktomek on 05.12.15.
 */
@UserScope
public class ThingsDataRepository implements ThingsRepository, ThingDetailsRepository {

    private final MemoryCache memoryCache;
    private final ThingsService thingsService;
    private final ModelDataMapper<ThingModel, Thing> modelDataMapper;


    private Action1<List<ThingModel>> saveThingsAction = new Action1<List<ThingModel>>() {
        @Override
        public void call(List<ThingModel> tweets) {
            memoryCache.saveThings(tweets);
        }
    };

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
                .doOnNext(saveThingsAction);
        if (forceSync) {
            return networkObservable;
        }
        Observable<List<ThingModel>> cacheObservable = memoryCache.getThings();
        return Observable.concat(cacheObservable, networkObservable).first();
    }

    public Observable<ThingDetailsModel> getThingDetails(boolean forceSync) {
        return Observable.create(new Observable.OnSubscribe<ThingDetailsModel>(){
            @Override
            public void call(Subscriber<? super ThingDetailsModel> observer) {
                try {
                    if (!observer.isUnsubscribed()) {

                        ThingDetailsModel thingDetailsModel = new ThingDetailsModel();

                        observer.onNext(thingDetailsModel);

                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        });
    }
}
