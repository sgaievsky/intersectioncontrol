package com.example.intersectioncontrol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.intersectioncontrol.entity.Intersection;
import com.example.intersectioncontrol.entity.StreetLight;
import com.example.intersectioncontrol.enumeration.LightRelation;
import com.example.intersectioncontrol.enumeration.LightType;
import com.example.intersectioncontrol.enumeration.StreetLightColor;
import com.example.intersectioncontrol.repository.IntersectionRepository;
import com.example.intersectioncontrol.repository.StreetLightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class IntersectioncontrolApplicationTests {

    @Autowired
    private IntersectionRepository intersectionRepository;

    @Autowired
    private StreetLightRepository streetLightRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void triggerLightsTest() {
        List<StreetLight> streetLightList = createMockStreetLight();

        Intersection intersection = new Intersection(streetLightList);
        assertNull(intersection.getId());
        intersectionRepository.save(intersection);
        streetLightList.forEach(streetLight -> {
            streetLight.setIntersection(intersection);
        });
        streetLightRepository.saveAll(streetLightList);
        assertNotNull(intersection.getId());
        List<StreetLight> masterStreetLights = intersection.getStreetLights().stream()
                .filter(s -> s.getLightType().equals(LightType.MASTER)).collect(Collectors.toList());
        List<StreetLight> slaveStreetLights = intersection.getStreetLights().stream()
                .filter(s -> s.getLightType().equals(LightType.SLAVE)).collect(Collectors.toList());
        masterStreetLights.forEach(masterStreetLight -> {
            assertNotNull(masterStreetLight.getId());
            assertEquals(StreetLightColor.RED, masterStreetLight.getCurrentColor());
        });
        slaveStreetLights.forEach(slaveStreetLight -> {
            assertNotNull(slaveStreetLight.getId());
            assertEquals(StreetLightColor.RED, slaveStreetLight.getCurrentColor());
        });

        intersection.triggerLights();
        masterStreetLights.forEach(masterStreetLight -> assertEquals(StreetLightColor.GREEN, masterStreetLight.getCurrentColor()));
        slaveStreetLights.forEach(slaveStreetLight -> assertEquals(StreetLightColor.RED, slaveStreetLight.getCurrentColor()));

        intersection.triggerLights();
        masterStreetLights.forEach(masterStreetLight -> assertEquals(StreetLightColor.RED, masterStreetLight.getCurrentColor()));
        slaveStreetLights.forEach(slaveStreetLight -> assertEquals(StreetLightColor.GREEN, slaveStreetLight.getCurrentColor()));
    }

    private List<StreetLight> createMockStreetLight() {
        List<StreetLight> streetLightList = new ArrayList<>();
        StreetLight masterStreetLightMain1 = new StreetLight(LightType.MASTER, LightRelation.MAIN);
        StreetLight masterStreetLightMain2 = new StreetLight(LightType.MASTER, LightRelation.MAIN);
        StreetLight masterStreetLightOpposite1 = new StreetLight(LightType.MASTER, LightRelation.OPPOSITE);
        StreetLight masterStreetLightOpposite2 = new StreetLight(LightType.MASTER, LightRelation.OPPOSITE);

        StreetLight slaveStreetLightMain1 = new StreetLight(LightType.SLAVE, LightRelation.MAIN);
        StreetLight slaveStreetLightMain2 = new StreetLight(LightType.SLAVE, LightRelation.MAIN);
        StreetLight slaveStreetLightOpposite1 = new StreetLight(LightType.SLAVE, LightRelation.OPPOSITE);
        StreetLight slaveStreetLightOpposite2 = new StreetLight(LightType.SLAVE, LightRelation.OPPOSITE);

        streetLightList.add(masterStreetLightMain1);
        streetLightList.add(masterStreetLightMain2);
        streetLightList.add(masterStreetLightOpposite1);
        streetLightList.add(masterStreetLightOpposite2);
        streetLightList.add(slaveStreetLightMain1);
        streetLightList.add(slaveStreetLightMain2);
        streetLightList.add(slaveStreetLightOpposite1);
        streetLightList.add(slaveStreetLightOpposite2);
        return streetLightList;
    }
}
