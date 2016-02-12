package com.rapifire.rapifireclient.view.adapter;

import com.rapifire.rapifireclient.domain.model.LatestTimeSeriesModel;
import com.rapifire.rapifireclient.domain.model.ThingModel;

public interface ThingLatestDataAdapterListener {
    public void onThingLatestDataItemViewClicked(LatestTimeSeriesModel latestTimeSeriesModel);
}
