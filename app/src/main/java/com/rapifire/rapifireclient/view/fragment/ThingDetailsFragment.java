package com.rapifire.rapifireclient.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.domain.model.LatestTimeSeriesModel;
import com.rapifire.rapifireclient.domain.model.ProductCommandModel;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.mvp.presenter.ThingDetailsPresenter;
import com.rapifire.rapifireclient.mvp.view.ThingDetailsView;
import com.rapifire.rapifireclient.view.activity.ThingDetailsActivity;
import com.rapifire.rapifireclient.view.activity.TimeSeriesActivity;
import com.rapifire.rapifireclient.view.adapter.ThingCommandsAdapter;
import com.rapifire.rapifireclient.view.adapter.ThingCommandsAdapterListener;
import com.rapifire.rapifireclient.view.adapter.ThingLatestDataAdapter;
import com.rapifire.rapifireclient.view.adapter.ThingLatestDataAdapterListener;
import com.rapifire.rapifireclient.view.adapter.ThingsAdapter;
import com.rapifire.rapifireclient.view.component.RandIcon;
import com.rapifire.rapifireclient.view.other.DurationFormatter;

import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by witek on 08.12.15.
 */
public class ThingDetailsFragment extends Fragment implements ThingDetailsView, SwipeRefreshLayout.OnRefreshListener, ThingLatestDataAdapterListener, ThingCommandsAdapterListener {
    private final static String TAG = "ThingDetailsFragment";

    @Inject
    ThingDetailsPresenter mThingDetailsPresenter;
    @Inject
    ThingLatestDataAdapter mAdapter;
    @Inject
    ThingCommandsAdapter mCommandsAdapter;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.swipe_to_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.profile_image_view)
    RandIcon mThingRandIcon;
    @Bind(R.id.thing_name_text_view)
    TextView mThingNameTextView;
    @Bind(R.id.thing_product_name_text_view)
    TextView mThingProductNameTextView;

    @Bind(R.id.thing_status_fontawsomeview)
    TextView mThingOnlineIcon;
    @Bind(R.id.thing_status_text_view)
    TextView mThingStatusTextView;
    @Bind(R.id.thing_last_publish_text_view)
    TextView mThingLastPublishTextView;

    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;

    private ThingModel thingModel;
    private ThingDetailsPagerAdapter thingDetailsPageAdapter;

    private DurationFormatter durationFormatter = new DurationFormatter();

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
            mAdapter.setOnItemViewClickedListener(this);
            mCommandsAdapter.setOnItemViewClickedListener(this);

            updateView(this.thingModel);
        }
        mThingDetailsPresenter.subscribe(this);

        thingDetailsPageAdapter = new ThingDetailsPagerAdapter(this.getChildFragmentManager(), mAdapter, mCommandsAdapter);

        viewPager.setAdapter(thingDetailsPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

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
        builder.setMessage(message);
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showMessage(int stringId) {
        this.showMessage(getString(stringId));
    }

    @Override
    public void showShortToast(int messageId) {
        Toast.makeText(getContext(), getString(messageId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setThingDetails(ThingDetailsModel thingDetails) {
        mThingRandIcon.setText(thingDetails.thingModel.name);
        mThingNameTextView.setText(thingDetails.thingModel.name);
        mThingProductNameTextView.setText(thingDetails.getProductModel().getName());

        if(thingDetails.isOnline()) {
            mThingOnlineIcon.setTextColor(Color.GREEN);
            mThingStatusTextView.setText("online");
        }else{
            mThingOnlineIcon.setTextColor(Color.GRAY);
            mThingStatusTextView.setText("offline");
        }

        mThingLastPublishTextView.setText(durationFormatter.formatDurationInMillisTolastOccurence(thingDetails.getMillisSinceLastPublish()));

        mAdapter.setItems(thingDetails.getLatestData().getLatestData());
    }

    @Override
    public void setProductCommands(List<ProductCommandModel> productCommands) {
        mCommandsAdapter.setItems(productCommands);
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

    @Override
    public void onThingLatestDataItemViewClicked(LatestTimeSeriesModel latestTimeSeriesModel) {
        mThingDetailsPresenter.onThingLatestDataItemClicked(latestTimeSeriesModel);
    }

    @Override
    public void onThingCommandItemViewClicked(ProductCommandModel productCommandModel) {
        Log.i(TAG, String.format("Comand with nae %s clicked", productCommandModel.getName()));

        mThingDetailsPresenter.sendCommandToThing(thingModel.thingId, productCommandModel.getName());
    }

    @Override
    public void navigateToTimeSeries(String seriesName) {
        Intent intent = new Intent(getActivity(), TimeSeriesActivity.class);
        intent.putExtra(TimeSeriesActivity.ARG_THING_ID, thingModel.thingId);
        intent.putExtra(TimeSeriesActivity.ARG_THING_TIMESEIRES_KEY, seriesName);

        getActivity().startActivity(intent);
    }

    private void updateView(ThingModel thingModel) {
        if(thingModel == null) {
            return;
        }

        if(mThingRandIcon != null) {
            mThingRandIcon.setText(thingModel.name);
        }

        if(mThingNameTextView != null) {
            mThingNameTextView.setText(thingModel.name);
        }

        if(mThingProductNameTextView != null) {
            mThingProductNameTextView.setText(thingModel.getProduct().getName());
        }
    }
}
