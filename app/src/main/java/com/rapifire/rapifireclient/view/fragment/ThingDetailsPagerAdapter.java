package com.rapifire.rapifireclient.view.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.view.adapter.ThingLatestDataAdapter;

/**
 * Created by witek on 15.02.16.
 */
public class ThingDetailsPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Graphs", "Commands" };

    private ThingDetailsModel thingDetails;
    ThingLatestDataAdapter mAdapter;

    public ThingDetailsPagerAdapter(FragmentManager fm, ThingLatestDataAdapter mAdapter) {
        super(fm);
        this.mAdapter = mAdapter;
    }


    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            ThingLatestDataFragment fragment = new ThingLatestDataFragment();
            fragment.setAdapter(mAdapter);

            return fragment;
        }

        return new ThingCommandsFragment();
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
