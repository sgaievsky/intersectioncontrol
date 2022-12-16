package com.example.intersectioncontrol.entity;

import com.example.intersectioncontrol.enumeration.SLColor;
import com.example.intersectioncontrol.enumeration.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class StreetLight implements Serializable {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "street_lights_pair_id")
    private StreetLightsPair streetLightsPair;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_color")
    private SLColor currentColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "broken_lights", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<SLColor> brokenLights = new ArrayList<>();
    private Status status;

    public StreetLight() {
        currentColor = SLColor.RED;
        status = Status.ACTIVE;
    }
}
