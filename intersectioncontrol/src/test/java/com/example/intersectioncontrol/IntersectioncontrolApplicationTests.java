package com.example.intersectioncontrol;

import com.example.intersectioncontrol.entity.Intersection;
import com.example.intersectioncontrol.entity.StreetLight;
import com.example.intersectioncontrol.entity.StreetLights;
import com.example.intersectioncontrol.entity.StreetLightsPair;
import com.example.intersectioncontrol.enumeration.StreetLightColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntersectioncontrolApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void triggerLightsTest() {
        StreetLight masterStreetLight1 = new StreetLight();
        StreetLight masterStreetLight2 = new StreetLight();
        StreetLight slaveStreetLight1 = new StreetLight();
        StreetLight slaveStreetLight2 = new StreetLight();

        StreetLightsPair masterSLPair = new StreetLightsPair(masterStreetLight1, masterStreetLight2);
        StreetLightsPair slaveSlPair = new StreetLightsPair(slaveStreetLight1, slaveStreetLight2);

        StreetLights streetLights = new StreetLights(masterSLPair, slaveSlPair);

        Intersection intersection = new Intersection(streetLights);
        Assertions.assertEquals(StreetLightColor.RED, masterStreetLight1.getCurrentColor());
        Assertions.assertEquals(StreetLightColor.RED, masterStreetLight2.getCurrentColor());
        Assertions.assertEquals(StreetLightColor.RED, slaveStreetLight1.getCurrentColor());
        Assertions.assertEquals(StreetLightColor.RED, slaveStreetLight2.getCurrentColor());

        intersection.triggerLights();
        Assertions.assertEquals(StreetLightColor.GREEN, masterStreetLight1.getCurrentColor());
        Assertions.assertEquals(StreetLightColor.GREEN, masterStreetLight2.getCurrentColor());
        Assertions.assertEquals(StreetLightColor.RED, slaveStreetLight1.getCurrentColor());
        Assertions.assertEquals(StreetLightColor.RED, slaveStreetLight2.getCurrentColor());

        intersection.triggerLights();
        Assertions.assertEquals(StreetLightColor.RED, masterStreetLight1.getCurrentColor());
        Assertions.assertEquals(StreetLightColor.RED, masterStreetLight2.getCurrentColor());
        Assertions.assertEquals(StreetLightColor.GREEN, slaveStreetLight1.getCurrentColor());
        Assertions.assertEquals(StreetLightColor.GREEN, slaveStreetLight2.getCurrentColor());
    }

}
