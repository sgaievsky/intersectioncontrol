package com.example.intersectioncontrol.entity;

import com.example.intersectioncontrol.enumeration.LightRelation;
import com.example.intersectioncontrol.enumeration.LightType;
import com.example.intersectioncontrol.enumeration.StreetLightColor;
import com.example.intersectioncontrol.enumeration.StreetLightStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class StreetLight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "intersection_id")
    private Intersection intersection;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_color")
    private StreetLightColor currentColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "broken_lights", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<StreetLightColor> brokenLights;

    @Enumerated(EnumType.STRING)
    @Column(name = "light_relation")
    private LightRelation lightRelation;

    @Enumerated(EnumType.STRING)
    @Column(name = "light_type")
    private LightType lightType;

    @Enumerated(EnumType.STRING)
    @Column(name = "street_light_status")
    private StreetLightStatus streetLightStatus;

    public StreetLight() {
    }

    public StreetLight(LightType lightType, LightRelation lightRelation) {
        this.currentColor = StreetLightColor.RED;
        this.brokenLights = new ArrayList<>();
        this.streetLightStatus = StreetLightStatus.ACTIVE;
        this.lightType = lightType;
        this.lightRelation = lightRelation;
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
