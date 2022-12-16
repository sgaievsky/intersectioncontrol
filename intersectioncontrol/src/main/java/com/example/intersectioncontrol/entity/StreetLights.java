package com.example.intersectioncontrol.entity;

import java.io.Serializable;

public class StreetLights implements Serializable {

    private StreetLightsPair masterSLPair;

    private StreetLightsPair slaveSLPair;

    public StreetLights() {
    }

    public StreetLights(StreetLightsPair masterSLPair, StreetLightsPair slaveSLPair) {
        this.masterSLPair = masterSLPair;
        this.slaveSLPair = slaveSLPair;
    }

    public void switchLights() {
        slaveSLPair.switchLights(masterSLPair.switchLights());
    }
}
