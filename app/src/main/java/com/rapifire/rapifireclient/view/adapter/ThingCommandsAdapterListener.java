package com.rapifire.rapifireclient.view.adapter;

import com.rapifire.rapifireclient.domain.model.LatestTimeSeriesModel;
import com.rapifire.rapifireclient.domain.model.ProductCommandModel;

public interface ThingCommandsAdapterListener {
    public void onThingCommandItemViewClicked(ProductCommandModel productCommandModel);
}
