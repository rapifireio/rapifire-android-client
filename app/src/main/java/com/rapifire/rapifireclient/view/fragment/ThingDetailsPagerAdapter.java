package com.rapifire.rapifireclient.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.view.adapter.ThingCommandsAdapter;
import com.rapifire.rapifireclient.view.adapter.ThingLatestDataAdapter;

/**
 * Created by witek on 15.02.16.
 */
public class ThingDetailsPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Graphs", "Commands" };

    private ThingDetailsModel thingDetails;
    private ThingLatestDataAdapter mAdapter;
    private ThingCommandsAdapter mCommandsAdapter;

    public ThingDetailsPagerAdapter(FragmentManager fm, ThingLatestDataAdapter mAdapter, ThingCommandsAdapter mCommandsAdapter) {
        super(fm);
        this.mAdapter = mAdapter;
        this.mCommandsAdapter = mCommandsAdapter;
    }


    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            ThingLatestDataFragment fragment = new ThingLatestDataFragment();
            fragment.setAdapter(mAdapter);

            return fragment;
        }

        ThingCommandsFragment fragment = new ThingCommandsFragment();
        fragment.setAdapter(mCommandsAdapter);

        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
