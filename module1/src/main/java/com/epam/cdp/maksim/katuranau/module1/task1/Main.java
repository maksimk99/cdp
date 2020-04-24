package com.epam.cdp.maksim.katuranau.module1.task1;

import com.epam.cdp.maksim.katuranau.module1.task1.exception.DuplicateException;
import com.epam.cdp.maksim.katuranau.module1.task1.exception.NotFoundException;
import com.epam.cdp.maksim.katuranau.module1.task1.exception.ValidationException;
import com.epam.cdp.maksim.katuranau.module1.task1.model.airplane.impl.CargoAirPlane;
import com.epam.cdp.maksim.katuranau.module1.task1.model.airplane.impl.CombatAirPlane;
import com.epam.cdp.maksim.katuranau.module1.task1.model.airplane.impl.PassengerAirPlane;
import com.epam.cdp.maksim.katuranau.module1.task1.model.helicopter.impl.CivilHelicopter;
import com.epam.cdp.maksim.katuranau.module1.task1.model.helicopter.impl.CombatHelicopter;
import com.epam.cdp.maksim.katuranau.module1.task1.model.quadrocopter.QuadCopter;
import com.epam.cdp.maksim.katuranau.module1.task1.model.quadrocopter.QuadCopterWithCamera;
import com.epam.cdp.maksim.katuranau.module1.task1.service.AirLineService;
import com.epam.cdp.maksim.katuranau.module1.task1.service.impl.AirLineServiceImpl;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        AirLineService airLineService = createAirLine();

        addAirCraftWithValidationError(airLineService);
        addAirCraftThatAlreadyExist(airLineService);
        sortByRangeOfFlight(airLineService);
        calculateTotalCapacity(airLineService);
        calculateTotalNumberOfPassengers(airLineService);
        findAirCraftByParameters(airLineService);
        getAirCraft(airLineService);
        getNotExistAirCraft(airLineService);
    }

    private static void getAirCraft(AirLineService airLineService) {
        System.out.println("\nAir craft with id = 2:\n" + airLineService.getAirCraft(2));
    }

    private static void getNotExistAirCraft(AirLineService airLineService) {
        try {
            airLineService.getAirCraft(32);
        } catch (NotFoundException ex) {
            System.out.println("Exception:\n" + ex.getMessage());
        }
    }

    private static void sortByRangeOfFlight(AirLineService airLineService) {
        System.out.println("\nSort by range of flight:");
        airLineService.sortByRangeOfFlight().forEach(System.out::println);
    }

    private static void calculateTotalCapacity(AirLineService airLineService) {
        System.out.println("\nTotal capacity: " + airLineService.calculateTotalCapacity());
    }

    private static void calculateTotalNumberOfPassengers(AirLineService airLineService) {
        System.out.println("\nTotal number of passengers: " + airLineService.calculateTotalNumberOfPassengers());
    }

    private static void findAirCraftByParameters(AirLineService airLineService) {
        int numberOfPassengersFrom = 10;
        int numberOfPassengersTo = 60;
        int carryingCapacityFrom = 2000;
        int carryingCapacityTo = 4000;
        int rangeOfFlightFrom = 600;
        int rangeOfFlightTo = 2000;
        System.out.println("\nParameters:\nnumberOfPassengersFrom = " + numberOfPassengersFrom
                + "\nnumberOfPassengersTo = " + numberOfPassengersTo
                + "\ncarryingCapacityFrom = " + carryingCapacityFrom
                + "\ncarryingCapacityTo = " + carryingCapacityTo
                + "\nrangeOfFlightFrom = " + rangeOfFlightFrom
                + "\nrangeOfFlightTo = " + rangeOfFlightTo
                + "\nResult:");
        airLineService.findAirCraftByParameters(numberOfPassengersFrom, numberOfPassengersTo, carryingCapacityFrom,
                carryingCapacityTo, rangeOfFlightFrom, rangeOfFlightTo).forEach(System.out::println);
    }

    private static AirLineService createAirLine() {
        CargoAirPlane cargoAirPlane = new CargoAirPlane(13.5, 42.6, 12,
                3000, 1600, 4.4, 3.4);
        PassengerAirPlane passengerAirPlane = new PassengerAirPlane(12.3, 35.5, 60,
                2000, 1200, 42.5, new BigDecimal("120.5"));
        CombatAirPlane combatAirPlane = new CombatAirPlane(12.3, 35.5, 60,
                2000, 1200, 1000, 900);
        CombatAirPlane combatAirPlane2 = new CombatAirPlane(12.3, 35.5, 60,
                2000, 1200, 1000, 900);

        CivilHelicopter civilHelicopter = new CivilHelicopter(9, 3000, 600,
                15, 13, new BigDecimal("350"), 300);
        CombatHelicopter combatHelicopter = new CombatHelicopter(2, 4000,
                900, 15, 15, 3, 400);

        QuadCopter quadCopter = new QuadCopter(2, 5, 4,
                3, true, 1.4);
        QuadCopterWithCamera quadCopterWithCamera = new QuadCopterWithCamera(2, 5,
                4, 3, true, 1.4, 16.5);

        AirLineService airLineService = new AirLineServiceImpl();
        airLineService.addAirCraft(cargoAirPlane);
        airLineService.addAirCraft(passengerAirPlane);
        airLineService.addAirCraft(combatAirPlane);
        airLineService.addAirCraft(civilHelicopter);
        airLineService.addAirCraft(combatHelicopter);
        airLineService.addAirCraft(quadCopter);
        airLineService.addAirCraft(quadCopterWithCamera);
        return airLineService;
    }

    private static void addAirCraftWithValidationError(AirLineService airLineService) {
        CombatHelicopter combatHelicopter = new CombatHelicopter(-9, 3000,
                -600, 15, 13, 3, 300);
        try {
            airLineService.addAirCraft(combatHelicopter);
        } catch (ValidationException ex) {
            System.out.println("\nException:\n" + ex.getMessage());
        }
    }

    private static void addAirCraftThatAlreadyExist(AirLineService airLineService) {
        CombatAirPlane combatAirPlane2 = new CombatAirPlane(12.3, 35.5, 60,
                2000, 1200, 1000, 900);
        try {
            airLineService.addAirCraft(combatAirPlane2);
        } catch (DuplicateException ex) {
            System.out.println("\nException:\n" + ex.getMessage());
        }
    }
}
