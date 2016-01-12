package com.rapifire.rapifireclient.domain.model;

import com.rapifire.rapifireclient.domain.interactor.TimeSeriesType;

/**
 * Created by witek on 07.01.16.
 */
public class LatestTimeSeriesModel {
    private String name;
    private TimeSeriesModel timeSeriesModel;

    public LatestTimeSeriesModel(String name, TimeSeriesModel timeSeriesModel) {
        this.name = name;
        this.timeSeriesModel = timeSeriesModel;
    }

    public String getName() {
        return this.name;
    }

    public long getDataTimeMillis() {
        return this.timeSeriesModel.getDataTimeMillis();
    }

    public String getValueAsString() {
        if(timeSeriesModel.getStringValue() != null){
            return timeSeriesModel.getStringValue();
        }

        if(timeSeriesModel.getDoubleValue() != null){
            return String.valueOf(timeSeriesModel.getDoubleValue());
        }

        throw new RuntimeException("This is bad: no value available");
    }

    public TimeSeriesModel getTimeSeriesModel() {
        return this.timeSeriesModel;
    }
}
