package com.rapifire.rapifireclient.domain.model;

public class ThingDetailsModel {
    public final ThingModel thingModel;

    public ThingDetailsModel(){
        this.thingModel = new ThingModel("Local created thing ID", "Local created thing name");
    }

    public ThingDetailsModel(ThingModel thingModel) {
        this.thingModel = thingModel;
    }
}
