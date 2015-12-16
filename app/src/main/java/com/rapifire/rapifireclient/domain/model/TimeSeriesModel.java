package com.rapifire.rapifireclient.domain.model;

/**
 * Created by ktomek on 08.12.15.
 */
public class TimeSeriesModel {
    private long dataTimeMillis;
    private Double doubleValue;
    private String stringValue;

    public TimeSeriesModel() {
    }

    public TimeSeriesModel(long dataTimeMillis, Double doubleValue, String stringValue) {
        this.dataTimeMillis = dataTimeMillis;
        this.doubleValue = doubleValue;
        this.stringValue = stringValue;
    }

    public long getDataTimeMillis() {
        return dataTimeMillis;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
