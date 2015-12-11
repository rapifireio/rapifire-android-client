package com.rapifire.rapifireclient.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.RapifireApp;
import com.rapifire.rapifireclient.data.ThingDetails;
import com.rapifire.rapifireclient.di.components.ThingDetailsComponent;
import com.rapifire.rapifireclient.di.components.ThingsComponent;
import com.rapifire.rapifireclient.di.module.ThingDetailsModule;
import com.rapifire.rapifireclient.di.module.ThingsModule;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.view.fragment.ThingDetailsFragment;
import com.rapifire.rapifireclient.view.fragment.ThingsFragment;

public class ThingDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThingDetailsFragment fragment = new ThingDetailsFragment();

        ThingModel thingModel = (ThingModel)this.getIntent().getSerializableExtra("thing.model");
        if(thingModel == null){
            throw new IllegalArgumentException("ThingModel passed by 'thing.model' extra must not be null");
        }

        Bundle args = new Bundle();
        args.putSerializable("thing.model", thingModel);
        fragment.setArguments(args);

        setupActivityComponent(fragment);
        setContentView(R.layout.activity_thing_details);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activityContent, fragment)
                .commit();
    }

    protected void setupActivityComponent(ThingDetailsFragment thingDetailsFragment) {
        final ThingDetailsComponent thingDetailsComponent = RapifireApp.get(this).getUserComponent()
                .plus(new ThingDetailsModule(this));
        thingDetailsComponent.inject(this);
        thingDetailsComponent.inject(thingDetailsFragment);
    }


    @Override
    public void finish() {
        super.finish();
        RapifireApp.get(this).releaseUserComponent();
    }
}
