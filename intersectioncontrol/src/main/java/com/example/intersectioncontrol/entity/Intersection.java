package com.example.intersectioncontrol.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Intersection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intersection_id", length = 16)
    private Long id;

    @OneToOne(mappedBy = "intersection")
    private StreetLights streetLights;

    public Intersection() {
        this.streetLights = new StreetLights();
    }

    public void triggerLights() {
        streetLights.switchLights();
    }
}
