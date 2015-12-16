package com.rapifire.rapifireclient.di.components;


import com.rapifire.rapifireclient.di.UserScope;
import com.rapifire.rapifireclient.di.module.ThingDetailsModule;
import com.rapifire.rapifireclient.di.module.ThingsModule;
import com.rapifire.rapifireclient.di.module.TimeSeriesModule;
import com.rapifire.rapifireclient.di.module.UserModule;

import dagger.Subcomponent;

/**
 * Created by ktomek on 05.12.15.
 */
@UserScope
@Subcomponent(modules = {UserModule.class})
public interface UserComponent {

    ThingsComponent plus(ThingsModule timelineModule);
    ThingDetailsComponent plus(ThingDetailsModule thingDetailsModule);
    TimeSeriesComponent plus(TimeSeriesModule timeSeriesModule);
}
