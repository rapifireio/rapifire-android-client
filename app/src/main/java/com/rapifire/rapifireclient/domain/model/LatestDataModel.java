package com.rapifire.rapifireclient.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by witek on 07.01.16.
 */
public class LatestDataModel {
    private List<LatestTimeSeriesModel> data = new ArrayList<LatestTimeSeriesModel>();

    public void addData(LatestTimeSeriesModel latestTimeSeriesModel) {
        data.add(latestTimeSeriesModel);
    }

    public List<LatestTimeSeriesModel> getLatestData() {
        return this.data;
    }
}
