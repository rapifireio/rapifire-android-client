package com.rapifire.rapifireclient.domain.model;

import com.rapifire.rapifireclient.domain.interactor.TimeSeriesType;

import java.util.List;

/**
 * Created by ktomek on 11.12.15.
 */
public class ChartModel {
    private TimeSeriesType type = TimeSeriesType.NONE;
    private List<ChartItemModel> item;

    public ChartModel(TimeSeriesType type, List<ChartItemModel> item) {
        this.type = type;
        this.item = item;
    }

    public TimeSeriesType getType() {
        return type;
    }

    public List<ChartItemModel> getItems() {
        return item;
    }
}
