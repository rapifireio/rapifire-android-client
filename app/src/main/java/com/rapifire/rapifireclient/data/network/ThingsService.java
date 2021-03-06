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

    @GET("things/{thingId}")
    Observable<ThingDetails> getThingDetails(@Path("thingId") String thingId);

    @GET("things/{thingId}/timeseries/{key}")
    Observable<List<TimeSeries>> getTimeSeries(@Path("thingId") String thingId,
                                               @Path("key") String key,
                                               @Query("lastMillis") long millis);
}
