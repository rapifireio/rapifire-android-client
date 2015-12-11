package com.rapifire.rapifireclient.domain.model;

import java.io.Serializable;

/**
 * Created by ktomek on 05.12.15.
 */
public class ThingModel implements Serializable {
    public final String thingId;
    public final String name;

    public ThingModel(String thingId, String name) {
        this.thingId = thingId;
        this.name = name;
    }
}
