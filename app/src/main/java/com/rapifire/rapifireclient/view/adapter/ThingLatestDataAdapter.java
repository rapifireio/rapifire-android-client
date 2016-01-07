package com.rapifire.rapifireclient.view.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.rapifire.rapifireclient.domain.model.LatestTimeSeriesModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.view.component.ThingItemView;
import com.rapifire.rapifireclient.view.component.ThingLatestDataItemView;

import javax.inject.Inject;

public class ThingLatestDataAdapter extends RecyclerViewAdapterBase<LatestTimeSeriesModel, ThingLatestDataItemView> {

    private Context context;
    private ThingsAdapterListener listener;

    @Inject
    public ThingLatestDataAdapter(final Context context) {
        super();
        this.context = context;
    }

    public void setOnItemViewClickedListener(ThingsAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    protected ThingLatestDataItemView onCreateItemView(ViewGroup parent, int viewType) {
        return ThingLatestDataItemView.build(context);
    }

    @Override
    protected void onItemViewClicked(ThingLatestDataItemView view) {

    }
}
