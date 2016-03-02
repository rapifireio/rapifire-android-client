package com.rapifire.rapifireclient.domain.model;

import java.io.Serializable;

/**
 * Created by ktomek on 05.12.15.
 */
public class ThingModel implements Serializable {
    public final String thingId;
    public final String name;
    private final ProductModel product;

    public ThingModel(String thingId, String name, ProductModel product) {
        this.thingId = thingId;
        this.name = name;
        this.product = product;
    }

    public ProductModel getProduct() {
        return product;
    }
}
