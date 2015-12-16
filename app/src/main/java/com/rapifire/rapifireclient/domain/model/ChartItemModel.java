package com.rapifire.rapifireclient.domain.model;

/**
 * Created by ktomek on 08.12.15.
 */
public class ChartItemModel {

    public final String name;
    public final double value;

    public ChartItemModel(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        ChartItemModel object = (ChartItemModel) o;
        return name.equals(object.name) && value == object.value;
    }

    @Override
    public String toString() {
        return name + ": " + value;
    }
}
