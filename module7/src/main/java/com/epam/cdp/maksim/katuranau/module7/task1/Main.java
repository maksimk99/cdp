package com.epam.cdp.maksim.katuranau.module7.task1;

import com.epam.cdp.maksim.katuranau.module7.task1.model.AirCraft;
import com.epam.cdp.maksim.katuranau.module7.task1.model.airplane.impl.CargoAirPlane;
import com.epam.cdp.maksim.katuranau.module7.task1.model.airplane.impl.CombatAirPlane;
import com.epam.cdp.maksim.katuranau.module7.task1.model.airplane.impl.PassengerAirPlane;
import com.epam.cdp.maksim.katuranau.module7.task1.model.helicopter.impl.CivilHelicopter;
import com.epam.cdp.maksim.katuranau.module7.task1.model.helicopter.impl.CombatHelicopter;
import com.epam.cdp.maksim.katuranau.module7.task1.model.quadrocopter.QuadCopter;
import com.epam.cdp.maksim.katuranau.module7.task1.model.quadrocopter.QuadCopterWithCamera;
import com.epam.cdp.maksim.katuranau.module7.task1.service.SerializationService;
import com.epam.cdp.maksim.katuranau.module7.task1.service.impl.SerializationServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String FILE_NAME = "temp.out";

    public static void main(String[] args) throws IOException {
        createListAircraft().forEach(Main::checkSerializationAndDeserialization);
        Files.delete(Paths.get(FILE_NAME));
    }

    private static List<AirCraft> createListAircraft() {
        return new ArrayList<AirCraft>() {{
            add(new CargoAirPlane(13.5, 42.6, 12, 3000,
                    1600, 4.4, 3.4));
            add(new PassengerAirPlane(12.3, 35.5, 60, 2000,
                    1200, 42.5, new BigDecimal("120.5")));
            add(new CombatAirPlane(12.3, 35.5, 60, 2000,
                    1200, 1000, 900));
            add(new CombatAirPlane(12.3, 35.5, 60, 2000,
                    1200, 1000, 900));
            add(new CivilHelicopter(9, 3000, 600, 15,
                    13, new BigDecimal("350"), 300));
            add(new CombatHelicopter(2, 4000, 900, 15,
                    15, 3, 400));
            add(new QuadCopter(2, 5, 4, 3,
                    true, 1.4));
            add(new QuadCopterWithCamera(2, 5, 4, 3,
                    true, 1.4, 16.5));
        }};
    }

    private static void checkSerializationAndDeserialization(AirCraft airCraft) {
        SerializationService<AirCraft> passengerAirPlaneSerializationService = new SerializationServiceImpl<>();

        passengerAirPlaneSerializationService.serializeObject(airCraft, FILE_NAME);
        AirCraft serializedObject = passengerAirPlaneSerializationService.deserializeObject(FILE_NAME);

        System.out.println("Object before serialization: " + airCraft);
        System.out.println("Object after serialization:  " + serializedObject);
        System.out.println();
    }
}
