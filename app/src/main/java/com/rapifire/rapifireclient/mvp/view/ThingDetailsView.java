package com.rapifire.rapifireclient.mvp.view;

import com.rapifire.rapifireclient.domain.model.ThingDetailsModel;

public interface ThingDetailsView extends MVPView {

    void setThingDetails(ThingDetailsModel thingDetails);

    void showProgress(boolean show);
    void showRefresh(boolean show);
}
