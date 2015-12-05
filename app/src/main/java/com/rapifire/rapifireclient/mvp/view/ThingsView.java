package com.rapifire.rapifireclient.mvp.view;

import com.rapifire.rapifireclient.domain.model.ThingModel;

import java.util.List;

/**
 * Created by ktomek on 05.12.15.
 */
public interface ThingsView extends MVPView {

    void setThings(List<ThingModel> tweets);

    void showProgress(boolean show);
    void showRefresh(boolean show);
}
