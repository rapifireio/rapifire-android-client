package com.rapifire.rapifireclient.view.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.rapifire.rapifireclient.domain.model.LatestTimeSeriesModel;
import com.rapifire.rapifireclient.domain.model.ProductCommandModel;
import com.rapifire.rapifireclient.view.component.ThingCommandItemView;
import com.rapifire.rapifireclient.view.component.ThingLatestDataItemView;

import javax.inject.Inject;

public class ThingCommandsAdapter extends RecyclerViewAdapterBase<ProductCommandModel, ThingCommandItemView> {

    private Context context;
    private ThingCommandsAdapterListener listener;

    @Inject
    public ThingCommandsAdapter(final Context context) {
        super();
        this.context = context;
    }

    public void setOnItemViewClickedListener(ThingCommandsAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    protected ThingCommandItemView onCreateItemView(ViewGroup parent, int viewType) {
        return ThingCommandItemView.build(context);
    }

    @Override
    protected void onItemViewClicked(ThingCommandItemView view) {
        if (listener != null) {
            listener.onThingCommandItemViewClicked(view.getData());
        }
    }
}
