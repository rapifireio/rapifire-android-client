package com.rapifire.rapifireclient.di.module;

import com.rapifire.rapifireclient.data.cache.MemoryCache;
import com.rapifire.rapifireclient.data.network.BasicAuthInterceptor;
import com.rapifire.rapifireclient.data.network.RapidfireSession;
import com.rapifire.rapifireclient.data.network.ThingsService;
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

    private RapidfireSession rapidfireSession;


    public UserModule(RapidfireSession rapidfireSession) {
        this.rapidfireSession = rapidfireSession;
    }

    @Provides
    @UserScope
    public RapidfireSession provideRapidfireSession() {
        return rapidfireSession;
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
    public ThingsDataRepository provideThingRepository(ThingsService thingsService,
                                                       MemoryCache memoryCache,
                                                       ThingModelDataMapper thingModelDataMapper) {
        return new ThingsDataRepository(memoryCache, thingsService, thingModelDataMapper);
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
    public BasicAuthInterceptor provideBasicAuthInterceptor(RapidfireSession rapidfireSession) {
        return new BasicAuthInterceptor(rapidfireSession.username, rapidfireSession.password);
    }
}
