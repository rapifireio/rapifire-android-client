package com.rapifire.rapifireclient.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.mvp.presenter.ThingsPresenter;
import com.rapifire.rapifireclient.mvp.view.ThingsView;
import com.rapifire.rapifireclient.view.activity.ThingDetailsActivity;
import com.rapifire.rapifireclient.view.activity.ThingsActivity;
import com.rapifire.rapifireclient.view.adapter.ThingsAdapter;
import com.rapifire.rapifireclient.view.adapter.ThingsAdapterListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ktomek on 05.12.15.
 */
public class ThingsFragment extends Fragment implements ThingsView,
        SwipeRefreshLayout.OnRefreshListener, ThingsAdapterListener {

    @Inject
    ThingsAdapter mAdapter;
    @Inject
    ThingsPresenter mThingsPresenter;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.timeline_recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe_to_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    public ThingsFragment() {
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
            contentView = inflater.inflate(R.layout.things_fragment, container, false);
            ButterKnife.bind(this, contentView);
            mAdapter.setOnItemViewClickedListener(this);
            mRecyclerView.setAdapter(mAdapter);
            mSwipeRefreshLayout.setOnRefreshListener(this);
        }
        mThingsPresenter.subscribe(this);
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
        mThingsPresenter.loadThings();
    }

    @Override
    public void onDestroyView() {
        mThingsPresenter.unsubscribe(this);
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
    public void setThings(List<ThingModel> things) {
        mAdapter.setItems(things);
        mAdapter.notifyDataSetChanged();
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
        mThingsPresenter.refreshThings();
    }

    @Override
    public void onThingItemViewClicked(ThingModel thingModel) {
        mThingsPresenter.onThingItemClicked(thingModel);
    }

    @Override
    public void navigateToThingDetails(ThingModel thingModel){
        Intent intent = new Intent(getActivity(), ThingDetailsActivity.class);
        intent.putExtra("thing.model", thingModel);

        getActivity().startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            mThingsPresenter.loadThings();
        }
    }
}
