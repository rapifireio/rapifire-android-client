package com.rapifire.rapifireclient.domain.model;

/**
 * Created by witek on 17.02.16.
 */
public class ProductModel {
    private String id;
    private String name;
    private int hartbeat;

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public int getHartbeat() {
        return hartbeat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHartbeat(int hartbeat) {
        this.hartbeat = hartbeat;
    }
}
