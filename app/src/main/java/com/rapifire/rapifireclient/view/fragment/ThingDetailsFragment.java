package com.rapifire.rapifireclient.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.mvp.presenter.ThingDetailsPresenter;
import com.rapifire.rapifireclient.mvp.view.ThingDetailsView;
import com.rapifire.rapifireclient.view.adapter.ThingLatestDataAdapter;
import com.rapifire.rapifireclient.view.adapter.ThingsAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by witek on 08.12.15.
 */
public class ThingDetailsFragment extends Fragment implements ThingDetailsView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    ThingDetailsPresenter mThingDetailsPresenter;
    @Inject
    ThingLatestDataAdapter mAdapter;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.swipe_to_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.thing_id_text_view)
    TextView mThingIdTextView;
    @Bind(R.id.thing_name_text_view)
    TextView mThingNameTextView;
    @Bind(R.id.thing_product_name_text_view)
    TextView mThingProductNameTextView;
    @Bind(R.id.thing_online_text_view)
    TextView mThingOnlineTextView;
    @Bind(R.id.thing_last_publish_text_view)
    TextView mThingLastPublishTextView;
    @Bind(R.id.latest_data_recycler_view)
    RecyclerView mLatestDataRecyclerView;

    private ThingModel thingModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.thingModel = (ThingModel)this.getArguments().getSerializable("thing.model");
        if(this.thingModel == null) {
            throw new IllegalArgumentException("ThingModel passed by 'thing.model' argument must not be null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.thing_details_fragment, container, false);
            ButterKnife.bind(this, contentView);
            mSwipeRefreshLayout.setOnRefreshListener(this);
            mLatestDataRecyclerView.setAdapter(mAdapter);
        }
        mThingDetailsPresenter.subscribe(this);
        return contentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        mThingDetailsPresenter.loadThingDetails(thingModel);
    }

    @Override
    public void onDestroyView() {
        mThingDetailsPresenter.unsubscribe(this);
        super.onDestroyView();
    }

    @Override
    public void showMessage(final String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message).setTitle(getString(R.string.warning));
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void setThingDetails(ThingDetailsModel thingDetails) {
        mThingIdTextView.setText(thingDetails.thingModel.thingId);
        mThingNameTextView.setText(thingDetails.thingModel.name);
        mThingProductNameTextView.setText(thingDetails.getProductName());
        mThingOnlineTextView.setText(thingDetails.isOnline() ? "true" : "false");
        if(thingDetails.getMillisSinceLastPublish() == null) {
            mThingLastPublishTextView.setText("never");
        } else {
            mThingLastPublishTextView.setText(String.format("%s milliseconds ago", thingDetails.getMillisSinceLastPublish()));
        }

        mAdapter.setItems(thingDetails.getLatestData().getLatestData());
    }

    @Override
    public void showProgress(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showRefresh(boolean show) {
        mSwipeRefreshLayout.setRefreshing(show);
    }

    @Override
    public void onRefresh() {
        mThingDetailsPresenter.refreshThingDetails(thingModel);
    }
}
