package com.rapifire.rapifireclient.data;

/**
 * Created by ktomek on 08.12.15.
 */
public class TimeSeries {
    private long dataTimeMillis;
    private Double doubleValue;
    private String stringValue;

    public TimeSeries(long dataTimeMillis, Double doubleValue, String stringValue) {
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
