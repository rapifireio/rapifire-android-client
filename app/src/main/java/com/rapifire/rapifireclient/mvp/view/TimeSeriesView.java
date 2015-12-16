package com.rapifire.rapifireclient.mvp.view;

import com.rapifire.rapifireclient.domain.model.ChartItemModel;

import java.util.List;

/**
 * Created by ktomek on 09.12.15.
 */
public interface TimeSeriesView extends MVPView {
    void showProgress(boolean show);

    void setLineData(List<ChartItemModel> data);
    void setPieData(List<ChartItemModel> data);
}
