package com.rapifire.rapifireclient.data;

/**
 * Created by ktomek on 04.12.15.
 */
public class Thing {
    private String thingId;
    private String name;

    public Thing(String thingId, String name) {
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
