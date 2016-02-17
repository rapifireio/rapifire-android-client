package com.rapifire.rapifireclient.data.mapper;

import com.rapifire.rapifireclient.data.Product;
import com.rapifire.rapifireclient.data.ThingDetails;
import com.rapifire.rapifireclient.data.TimeSeries;
import com.rapifire.rapifireclient.domain.model.LatestDataModel;
import com.rapifire.rapifireclient.domain.model.LatestTimeSeriesModel;
import com.rapifire.rapifireclient.domain.model.ProductModel;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.domain.model.TimeSeriesModel;

import java.util.Map;

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
        thingDetailsModel.setOnline(thingDetails.isOnline());
        thingDetailsModel.setMillisSinceLastPublish(thingDetails.getMillisecondsSinceLastPublish());
        thingDetailsModel.setLatestData(transformLatestData(thingDetails.getLatestData()));

        thingDetailsModel.setProductModel(transformProduct(thingDetails.getProduct()));

        return thingDetailsModel;
    }

    private LatestDataModel transformLatestData(Map<String, TimeSeries> latestData) {
        if(latestData == null){
            return new LatestDataModel();
        }

        TimeSeriesModelDataMapper mapper = new TimeSeriesModelDataMapper();

        LatestDataModel latestDataModel = new LatestDataModel();

        for(String seriesName: latestData.keySet()) {
            TimeSeriesModel timeSeriesModel = mapper.transform(latestData.get(seriesName));
            latestDataModel.addData(new LatestTimeSeriesModel(seriesName, timeSeriesModel));
        }

        return latestDataModel;
    }

    private ProductModel transformProduct(Product product) {
        ProductModel result = new ProductModel();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setHartbeat(product.getHartbeat());

        return result;
    }
}
