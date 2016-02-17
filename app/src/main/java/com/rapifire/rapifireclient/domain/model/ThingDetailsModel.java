package com.rapifire.rapifireclient.domain.model;

public class ThingDetailsModel {
    public final ThingModel thingModel;
    private boolean online;
    private Long millisSinceLastPublish;
    private ProductModel productModel;
    private LatestDataModel latestDataModel = new LatestDataModel();

    public ThingDetailsModel() {
        this.thingModel = new ThingModel("Local created thing ID", "Local created thing name");
    }

    public ThingDetailsModel(ThingModel thingModel) {
        this.thingModel = thingModel;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isOnline() {
        return this.online;
    }

    public void setMillisSinceLastPublish(Long millisSinceLastPublish) {
        this.millisSinceLastPublish = millisSinceLastPublish;
    }

    public Long getMillisSinceLastPublish() {
        return this.millisSinceLastPublish;
    }

    public void setProductModel(ProductModel product) {
        this.productModel = product;
    }

    public ProductModel getProductModel() {
        return this.productModel;
    }

    public void setLatestData(LatestDataModel latestDataModel) {
        this.latestDataModel = latestDataModel;
    }

    public LatestDataModel getLatestData() {
        return this.latestDataModel;
    }
}
