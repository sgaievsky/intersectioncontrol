package com.example.intersectioncontrol.entity;


import com.example.intersectioncontrol.enumeration.SLColor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class StreetLightsPair implements Serializable {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "street_light_id")
    private StreetLights streetLights;
    @OneToOne(mappedBy = "streetLightsPair")
    private StreetLight sl1;
    @OneToOne(mappedBy = "streetLightsPair")
    private StreetLight sl2;

    public StreetLightsPair() {
        sl1 = new StreetLight();
        sl2 = new StreetLight();
    }

    public StreetLightsPair switchLights(SLColor slColor) {
        switch (slColor) {
            case RED:
                sl1.setCurrentColor(SLColor.GREEN);
                sl2.setCurrentColor(SLColor.GREEN);
                break;
            default:
                sl1.setCurrentColor(SLColor.RED);
                sl2.setCurrentColor(SLColor.RED);
                break;
        }
        return this;
    }
}
