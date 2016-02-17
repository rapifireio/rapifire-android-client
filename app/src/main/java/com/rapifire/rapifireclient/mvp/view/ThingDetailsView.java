package com.rapifire.rapifireclient.mvp.view;

import com.rapifire.rapifireclient.data.ProductCommand;
import com.rapifire.rapifireclient.domain.model.ProductCommandModel;
import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;

import java.util.List;

public interface ThingDetailsView extends MVPView {

    void setThingDetails(ThingDetailsModel thingDetails);
    void setProductCommands(List<ProductCommandModel> productCommands);
    void showProgress(boolean show);
    void showRefresh(boolean show);
    void navigateToTimeSeries(String seriesName);
}
