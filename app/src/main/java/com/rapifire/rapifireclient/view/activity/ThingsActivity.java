package com.rapifire.rapifireclient.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.RapifireApp;
import com.rapifire.rapifireclient.di.components.ThingsComponent;
import com.rapifire.rapifireclient.di.module.ThingsModule;
import com.rapifire.rapifireclient.view.fragment.ThingsFragment;

/**
 * Created by ktomek on 05.12.15.
 */
public class ThingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThingsFragment fragment = new ThingsFragment();
        setupActivityComponent(fragment);
        setContentView(R.layout.activity_things);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.timeline_list, fragment)
                .commit();

    }

    protected void setupActivityComponent(ThingsFragment timelineFragment) {
        final ThingsComponent timelineComponent = RapifireApp.get(this).getUserComponent()
                .plus(new ThingsModule(this));
        timelineComponent.inject(this);
        timelineComponent.inject(timelineFragment);
    }


    @Override
    public void finish() {
        super.finish();
        RapifireApp.get(this).releaseUserComponent();
    }
}
