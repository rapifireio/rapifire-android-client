package com.rapifire.rapifireclient.domain.model;

/**
 * Created by witek on 17.02.16.
 */
public class ProductCommandModel {
    private String name;
    private String type;
    private String visibility;
    private String payload;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getPayload() {
        return payload;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
