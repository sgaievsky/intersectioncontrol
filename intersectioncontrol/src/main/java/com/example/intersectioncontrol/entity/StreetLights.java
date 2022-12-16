package com.example.intersectioncontrol.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class StreetLights implements Serializable {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "intersection_id")
    private Intersection intersection;

    @OneToOne(mappedBy = "streetLights")
    private StreetLightsPair masterSLPair;

    @OneToOne(mappedBy = "streetLights")
    private StreetLightsPair slaveSLPair;

    public StreetLights() {
        masterSLPair = new StreetLightsPair();
        slaveSLPair = new StreetLightsPair();
    }

    public void switchLights() {
        StreetLightsPair currentStateMasterPair = masterSLPair.switchLights(masterSLPair.getSl1().getCurrentColor());
        slaveSLPair.switchLights(currentStateMasterPair.getSl1().getCurrentColor());
    }
}
