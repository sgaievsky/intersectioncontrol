package com.example.intersectioncontrol.entity;


import com.example.intersectioncontrol.enumeration.StreetLightColor;

import java.io.Serializable;

public class StreetLightsPair implements Serializable {

    private StreetLight sl1;
    private StreetLight sl2;

    public StreetLightsPair() {
    }

    public StreetLightsPair(StreetLight sl1, StreetLight sl2) {
        this.sl1 = sl1;
        this.sl2 = sl2;
    }

    public StreetLightColor switchLights() {
        sl1.switchLights();
        return sl2.switchLights();
    }

    public void switchLights(StreetLightColor streetLightColor) {
        sl1.switchLights(streetLightColor);
        sl2.switchLights(streetLightColor);
    }
}
