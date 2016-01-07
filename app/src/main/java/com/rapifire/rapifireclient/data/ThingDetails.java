package com.rapifire.rapifireclient.data;

import java.util.Map;

public class ThingDetails {
    private String thingId;
    private String name;
    private boolean online;
    private Long millisSinceLastPublish;
    private Product product;
    private Map<String, TimeSeries> latestData;

    public ThingDetails(String thingId, String name) {
        this.thingId = thingId;
        this.name = name;
    }

    public String getThingId() {
        return thingId;
    }

    public String getName() {
        return name;
    }

    public boolean isOnline() {
        return this.online;
    }

    public Long getMillisecondsSinceLastPublish() {
        return this.millisSinceLastPublish;
    }

    public Product getProduct() {
        return this.product;
    }

    public Map<String, TimeSeries> getLatestData() {
        return this.latestData;
    }
}
