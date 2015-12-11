package com.rapifire.rapifireclient.di.module;

import com.rapifire.rapifireclient.data.cache.MemoryCache;
import com.rapifire.rapifireclient.data.mapper.ThingDetailsModelDataMapper;
import com.rapifire.rapifireclient.data.network.BasicAuthInterceptor;
import com.rapifire.rapifireclient.data.network.RapifireSession;
import com.rapifire.rapifireclient.data.network.ThingsService;
import com.rapifire.rapifireclient.data.repository.ThingDetailsDataRepository;
import com.rapifire.rapifireclient.data.repository.ThingsDataRepository;
import com.rapifire.rapifireclient.di.UserScope;
import com.rapifire.rapifireclient.data.mapper.ThingModelDataMapper;
import com.squareup.okhttp.OkHttpClient;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

/**
 * Created by ktomek on 05.12.15.
 */
@Module
public class UserModule {

    private RapifireSession rapifireSession;


    public UserModule(RapifireSession rapifireSession) {
        this.rapifireSession = rapifireSession;
    }

    @Provides
    @UserScope
    public RapifireSession provideRapifireSession() {
        return rapifireSession;
    }

    @Provides
    @UserScope
    public MemoryCache provideMemoryCache() {
        return new MemoryCache();
    }

    @Provides
    @UserScope
    public ThingModelDataMapper provideThingModelDataMapper() {
        return new ThingModelDataMapper();
    }

    @Provides
    @UserScope
    public ThingDetailsModelDataMapper provideThingDetailsModelDataMapper() {
        return new ThingDetailsModelDataMapper();
    }

    @Provides
    @UserScope
    public ThingsDataRepository provideThingRepository(ThingsService thingsService,
                                                       MemoryCache memoryCache,
                                                       ThingModelDataMapper thingModelDataMapper) {
        return new ThingsDataRepository(memoryCache, thingsService, thingModelDataMapper);
    }

    @Provides
    @UserScope
    public ThingDetailsDataRepository provideThingDetailsRepository(ThingsService thingsService,
                                                       MemoryCache memoryCache,
                                                       ThingDetailsModelDataMapper thingDetailsModelDataMapper) {
        return new ThingDetailsDataRepository(memoryCache, thingsService, thingDetailsModelDataMapper);
    }


    @Provides
    @UserScope
    public ThingsService provideThingsService(Retrofit.Builder builder, OkHttpClient client,
                                              BasicAuthInterceptor interceptor) {
        client.interceptors().clear();
        client.interceptors().add(interceptor);
        builder.client(client);
        Retrofit retrofit = builder.build();
        return retrofit.create(ThingsService.class);
    }

    @Provides
    @UserScope
    public BasicAuthInterceptor provideBasicAuthInterceptor(RapifireSession rapifireSession) {
        return new BasicAuthInterceptor(rapifireSession.username, rapifireSession.password);
    }
}
