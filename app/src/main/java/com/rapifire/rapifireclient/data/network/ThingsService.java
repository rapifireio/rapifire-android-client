package com.rapifire.rapifireclient.data.network;

import com.rapifire.rapifireclient.data.Thing;
import com.rapifire.rapifireclient.data.ThingDetails;
import com.rapifire.rapifireclient.data.TimeSeries;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by ktomek on 05.12.15.
 */
public interface ThingsService {

    @GET("things")
    Observable<List<Thing>> getThings();

    @GET("thing/{thingId}")
    Observable<ThingDetails> getThingDetails(@Path("thingId") String thingId);

    @GET("thing/{thing}/timeseries/usr")
    Observable<List<TimeSeries>> getTimeSeries(@Path("thing") String thing,
                                               @Query("lastMillis") long millis);
}
