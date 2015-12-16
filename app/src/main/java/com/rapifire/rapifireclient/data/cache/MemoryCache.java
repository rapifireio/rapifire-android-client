package com.rapifire.rapifireclient.data.cache;

import com.rapifire.rapifireclient.di.UserScope;
import com.rapifire.rapifireclient.domain.model.ThingModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by ktomek on 05.12.15.
 */
@UserScope
public class MemoryCache {

    private List<ThingModel> things = new ArrayList<>();

    public Observable<List<ThingModel>> getThings() {
        if (things.size() == 0) {
            return Observable.empty();
        }
        return Observable.just(things);
    }

    public void saveThings(List<ThingModel> things) {
        this.things = things;
    }

    public void saveThings(ThingModel thingsModel) {
        if (things == null) {
            things = new ArrayList<>();
        }
        things.add(0, thingsModel);
    }
}
