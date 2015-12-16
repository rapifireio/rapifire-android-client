package com.rapifire.rapifireclient.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.RapifireApp;
import com.rapifire.rapifireclient.di.components.TimeSeriesComponent;
import com.rapifire.rapifireclient.di.module.TimeSeriesModule;
import com.rapifire.rapifireclient.view.fragment.TimeSeriesFragment;

/**
 * Created by ktomek on 09.12.15.
 */
public class TimeSeriesActivity extends AppCompatActivity {

    public static final String ARG_THING_ID = "ARG_THING_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String thingId = getIntent().getStringExtra(ARG_THING_ID);
        TimeSeriesFragment fragment = new TimeSeriesFragment();
        setupActivityComponent(fragment, thingId);
        setContentView(R.layout.activity_things);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.timeline_list, fragment)
                .commit();

    }

    protected void setupActivityComponent(TimeSeriesFragment timeSeriesFragment, final String thingId) {
        final TimeSeriesComponent timeSeriesComponent = RapifireApp.get(this).getUserComponent()
                .plus(new TimeSeriesModule(this, thingId));
        timeSeriesComponent.inject(timeSeriesFragment);
    }
}
