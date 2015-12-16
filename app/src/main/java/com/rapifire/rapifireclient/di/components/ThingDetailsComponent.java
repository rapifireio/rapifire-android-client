package com.rapifire.rapifireclient.di.components;

import com.rapifire.rapifireclient.di.ActivityScope;
import com.rapifire.rapifireclient.di.module.ThingDetailsModule;
import com.rapifire.rapifireclient.view.activity.ThingDetailsActivity;
import com.rapifire.rapifireclient.view.fragment.ThingDetailsFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ThingDetailsModule.class)
public interface ThingDetailsComponent {
    ThingDetailsActivity inject(ThingDetailsActivity thingDetailsActivity);
    ThingDetailsFragment inject(ThingDetailsFragment thingDetailsFragment);
}
