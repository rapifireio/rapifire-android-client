package com.rapifire.rapifireclient.data;

public class ThingDetails {
    private String thingId;
    private String name;

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
}
