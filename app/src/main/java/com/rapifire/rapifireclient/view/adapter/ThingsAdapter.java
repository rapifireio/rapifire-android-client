package com.rapifire.rapifireclient.view.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.rapifire.rapifireclient.domain.model.ThingModel;
import com.rapifire.rapifireclient.view.component.ThingItemView;

import javax.inject.Inject;

/**
 * Created by ktomek on 05.12.15.
 */
public class ThingsAdapter extends RecyclerViewAdapterBase<ThingModel, ThingItemView> {

    private Context context;

    @Inject
    public ThingsAdapter(final Context context) {
        super();
        this.context = context;
    }

    @Override
    protected ThingItemView onCreateItemView(ViewGroup parent, int viewType) {
        return ThingItemView.build(context);
    }
}
