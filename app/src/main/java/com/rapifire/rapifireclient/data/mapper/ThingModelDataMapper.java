package com.rapifire.rapifireclient.data.mapper;

import com.rapifire.rapifireclient.data.Product;
import com.rapifire.rapifireclient.data.Thing;
import com.rapifire.rapifireclient.domain.model.ProductModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by ktomek on 05.12.15.
 */
public class ThingModelDataMapper implements ModelDataMapper<ThingModel, Thing> {

    private ProductTransformer productTransformer = new ProductTransformer();

    @Override
    public ThingModel transform(Thing thing) {
        return transformThing(thing);
    }

    @Override
    public List<ThingModel> transform(List<Thing> things) {
        final int size = things.size();
        final List<ThingModel> thingModelList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            final Thing tweet = things.get(i);
            thingModelList.add(transform(tweet));
        }
        return thingModelList;
    }

    @Override
    public Observable<List<ThingModel>> call(List<Thing> things) {
        return Observable.just(transform(things));
    }

    private ThingModel transformThing(Thing thing) {
        final String id = thing.getThingId();
        final String name = thing.getName();
        final ProductModel product = productTransformer.transformProduct(thing.getProduct());
        final ThingModel thingModel = new ThingModel(id, name, product);

        return thingModel;
    }
}
