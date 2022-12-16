package com.example.intersectioncontrol.entity;

import com.example.intersectioncontrol.enumeration.StreetLightColor;
import com.example.intersectioncontrol.enumeration.StreetLightStatus;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class StreetLight implements Serializable {

    private Long id;
    private StreetLightColor currentColor;
    private List<StreetLightColor> brokenLights;
    private StreetLightStatus status;

    public StreetLight() {
        id = new Random().nextLong();
        currentColor = StreetLightColor.RED;
        brokenLights = new ArrayList<>();
        status = StreetLightStatus.ACTIVE;
    }

    public StreetLightColor switchLights() {
        if (currentColor == StreetLightColor.RED) {
            currentColor = StreetLightColor.GREEN;
        } else {
            currentColor = StreetLightColor.RED;
        }
        return currentColor;
    }

    public void switchLights(StreetLightColor streetLightColor) {
        if (streetLightColor == StreetLightColor.RED) {
            currentColor = StreetLightColor.GREEN;
        } else {
            currentColor = StreetLightColor.RED;
        }
    }

}
