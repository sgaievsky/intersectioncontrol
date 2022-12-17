package com.example.intersectioncontrol.entity;

import com.example.intersectioncontrol.enumeration.LightType;
import com.example.intersectioncontrol.enumeration.StreetLightColor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Intersection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intersection_id", length = 16)
    private Long id;

    @OneToMany(mappedBy = "intersection")
    private List<StreetLight> streetLights;

    public Intersection() {
    }

    public Intersection(List<StreetLight> streetLights) {
        this.streetLights = streetLights;
    }

    public void triggerLights() {
        List<StreetLight> masterStreetLight = streetLights.stream()
                .filter(s -> s.getLightType().equals(LightType.MASTER)).collect(Collectors.toList());
        List<StreetLight> slaveStreetLight = streetLights.stream().filter(s -> s.getLightType().equals(LightType.SLAVE))
                .collect(Collectors.toList());

        masterStreetLight.forEach(StreetLight::switchLights);
        slaveStreetLight.forEach(streetLight -> {
            streetLight.switchLights(getCurrentColorFromList(masterStreetLight));
        });
    }

    private StreetLightColor getCurrentColorFromList(List<StreetLight> streetLights) {
        return streetLights.stream().map(StreetLight::getCurrentColor).findFirst().orElse(StreetLightColor.GREEN);
    }
}
