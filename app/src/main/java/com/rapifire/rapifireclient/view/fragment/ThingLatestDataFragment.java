package com.rapifire.rapifireclient.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.domain.model.LatestTimeSeriesModel;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.mvp.presenter.ThingDetailsPresenter;
import com.rapifire.rapifireclient.view.adapter.ThingLatestDataAdapter;
import com.rapifire.rapifireclient.view.adapter.ThingLatestDataAdapterListener;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by witek on 15.02.16.
 */
public class ThingLatestDataFragment extends Fragment {

    ThingLatestDataAdapter mAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView mLatestDataRecyclerView;

    public void setAdapter(ThingLatestDataAdapter mAdapter) {
        this.mAdapter = mAdapter;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.thing_latest_data_fragment, container, false);
            ButterKnife.bind(this, contentView);

            mLatestDataRecyclerView.setAdapter(mAdapter);
        }

        return contentView;
    }
}
