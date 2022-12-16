package com.example.intersectioncontrol.entity;

import java.io.Serializable;

public class Intersection implements Serializable {

    private StreetLights streetLights;

    public Intersection() {
    }

    public Intersection(StreetLights streetLights) {
        this.streetLights = streetLights;
    }

    public void triggerLights() {
        streetLights.switchLights();
    }
}
