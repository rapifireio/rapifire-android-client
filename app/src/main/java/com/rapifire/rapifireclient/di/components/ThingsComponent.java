package com.rapifire.rapifireclient.di.components;

import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.di.module.ThingsModule;
import com.rapifire.rapifireclient.view.activity.ThingsActivity;
import com.rapifire.rapifireclient.view.fragment.ThingsFragment;

import dagger.Subcomponent;

/**
 * Created by ktomek on 05.12.15.
 */
@ActivityScope
@Subcomponent(modules = ThingsModule.class)
public interface ThingsComponent {
    ThingsActivity inject(ThingsActivity thingsActivity);
    ThingsFragment inject(ThingsFragment thingsFragment);
}
