package com.rapifire.rapifireclient.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;
import com.rapifire.rapifireclient.mvp.presenter.ThingDetailsPresenter;
import com.rapifire.rapifireclient.mvp.view.ThingDetailsView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by witek on 08.12.15.
 */
public class ThingDetailsFragment extends Fragment implements ThingDetailsView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    ThingDetailsPresenter mThingDetailsPresenter;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.swipe_to_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.thing_id_text_view)
    TextView mThingIdTextView;
    @Bind(R.id.thing_name_text_tiew)
    TextView mThingNameTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = super.onCreateView(inflater, container, savedInstanceState);
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.thing_details_fragment, container, false);
            ButterKnife.bind(this, contentView);
            mSwipeRefreshLayout.setOnRefreshListener(this);
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
        mThingDetailsPresenter.loadThingDetails();
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
        mThingDetailsPresenter.refreshThingDetails();
    }
}
