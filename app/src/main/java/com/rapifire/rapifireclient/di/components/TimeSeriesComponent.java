package com.rapifire.rapifireclient.di.components;

import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.di.module.TimeSeriesModule;
import com.rapifire.rapifireclient.view.fragment.TimeSeriesFragment;

import dagger.Subcomponent;

/**
 * Created by ktomek on 09.12.15.
 */
@ActivityScope
@Subcomponent(modules = TimeSeriesModule.class)
public interface TimeSeriesComponent {
    TimeSeriesFragment inject(TimeSeriesFragment timeSeriesFragment);
}