package com.epam.cdp.maksim.katuranau.module2;

import com.epam.cdp.maksim.katuranau.module2.model.AirCraft;
import com.epam.cdp.maksim.katuranau.module2.model.airplane.AirPlane;
import com.epam.cdp.maksim.katuranau.module2.model.airplane.impl.CargoAirPlane;
import com.epam.cdp.maksim.katuranau.module2.model.airplane.impl.CombatAirPlane;
import com.epam.cdp.maksim.katuranau.module2.model.airplane.impl.PassengerAirPlane;
import com.epam.cdp.maksim.katuranau.module2.model.helicopter.Helicopter;
import com.epam.cdp.maksim.katuranau.module2.model.helicopter.impl.CivilHelicopter;
import com.epam.cdp.maksim.katuranau.module2.model.helicopter.impl.CombatHelicopter;
import com.epam.cdp.maksim.katuranau.module2.model.quadrocopter.QuadCopter;
import com.epam.cdp.maksim.katuranau.module2.model.quadrocopter.QuadCopterWithCamera;
import com.epam.cdp.maksim.katuranau.module2.processor.ProdCodeProcessor;
import com.epam.cdp.maksim.katuranau.module2.processor.ThisCodeSmellsProcessor;
import com.epam.cdp.maksim.katuranau.module2.service.AirLineService;
import com.epam.cdp.maksim.katuranau.module2.service.impl.AirLineServiceImpl;
import com.epam.cdp.maksim.katuranau.module2.service.impl.ProdRunnerImpl;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        AirLineService airLineService = createAirLine();

        addAirCraftWithValidationError(airLineService);
        addAirCraftThatAlreadyExist(airLineService);
        sortByRangeOfFlight(airLineService);
        calculateTotalCapacity(airLineService);
        calculateTotalNumberOfPassengers(airLineService);
        findAirCraftByParameters(airLineService);
        getAirCraft(airLineService);
        getNotExistAirCraft(airLineService);
        printMetadataOfAllClasses();
        processThisCodeSmellsAnnotations();
        processProdCodeAnnotation();
    }

    private static void getAirCraft(AirLineService airLineService) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method getAirCraftMethod = AirLineServiceImpl.class.getDeclaredMethod("getAirCraft", int.class);
        System.out.println("\nAir craft with id = 2:\n" + getAirCraftMethod.invoke(airLineService, 2));
    }

    private static void getNotExistAirCraft(AirLineService airLineService) throws NoSuchMethodException {
        Method getAirCraftMethod = AirLineServiceImpl.class.getDeclaredMethod("getAirCraft", int.class);
        try {
            getAirCraftMethod.invoke(airLineService, 32);
        } catch (InvocationTargetException | IllegalAccessException ex) {
            System.out.println("\nException:\n" + ex.getCause().getMessage());
        }
    }

    //I use this com.epam.cdp.maksim.katuranau.module2.annotation because I cast result of calculateTotalCapacityMethod to List without generics
    @SuppressWarnings("unchecked")
    private static void sortByRangeOfFlight(AirLineService airLineService) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method calculateTotalCapacityMethod = AirLineServiceImpl.class.getDeclaredMethod("sortByRangeOfFlight");
        System.out.println("\nSort by range of flight:");
        ((List) calculateTotalCapacityMethod.invoke(airLineService)).forEach(System.out::println);
    }

    private static void calculateTotalCapacity(AirLineService airLineService) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method calculateTotalCapacityMethod = AirLineServiceImpl.class.getDeclaredMethod("calculateTotalCapacity");
        System.out.println("\nTotal capacity: " + calculateTotalCapacityMethod.invoke(airLineService));
    }

    private static void calculateTotalNumberOfPassengers(AirLineService airLineService) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method calculateTotalCapacityMethod = AirLineServiceImpl.class.
                getDeclaredMethod("calculateTotalNumberOfPassengers");
        System.out.println("\nTotal number of passengers: " + calculateTotalCapacityMethod.invoke(airLineService));
    }

    @SuppressWarnings("unchecked")
    private static void findAirCraftByParameters(AirLineService airLineService) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Method findAirCraftByParameters = AirLineServiceImpl.class.getDeclaredMethod("findAirCraftByParameters",
                int.class, int.class, int.class, int.class, int.class, int.class);
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
        ((List) findAirCraftByParameters.invoke(airLineService, numberOfPassengersFrom,
                numberOfPassengersTo, carryingCapacityFrom, carryingCapacityTo, rangeOfFlightFrom, rangeOfFlightTo))
                .forEach(System.out::println);
    }

    private static AirLineService createAirLine() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchFieldException {
        CargoAirPlane cargoAirPlane = CargoAirPlane.class.getConstructor(double.class, double.class, int.class,
                int.class, int.class, double.class, double.class).newInstance(13.5, 42.6, 12, 3000, 1600,
                4.4, 3.4);
        PassengerAirPlane passengerAirPlane = PassengerAirPlane.class.getConstructor(double.class, double.class,
                int.class, int.class, int.class, double.class, BigDecimal.class).newInstance(12.3, 35.5, 60,
                2000, 1200, 42.5, new BigDecimal("120.5"));
        CombatAirPlane combatAirPlane = CombatAirPlane.class.getConstructor(double.class, double.class, int.class,
                int.class, int.class, int.class, int.class).newInstance(12.3, 35.5, 60, 2000, 1200,
                1000, 900);

        CivilHelicopter civilHelicopter = CivilHelicopter.class.getConstructor(int.class, int.class, int.class,
                int.class, int.class, BigDecimal.class, int.class).newInstance(9, 3000, 600, 15, 13,
                new BigDecimal("350"), 300);
        CombatHelicopter combatHelicopter = CombatHelicopter.class.getConstructor(int.class, int.class, int.class,
                int.class, int.class, int.class, int.class).newInstance(2, 4000, 900, 15, 15, 3, 400);

        QuadCopter quadCopter = QuadCopter.class.getConstructor(int.class, int.class, int.class, int.class,
                boolean.class, double.class).newInstance(2, 5, 4, 3, true, 1.4);
        QuadCopterWithCamera quadCopterWithCamera = QuadCopterWithCamera.class.getConstructor(int.class, int.class,
                int.class, int.class, boolean.class, double.class, double.class).newInstance(2, 5, 4, 3, true,
                1.4, 16.5);

        Field field1 = combatHelicopter.getClass().getDeclaredField("crew");
        field1.setAccessible(true);
        field1.set(combatHelicopter, 3);
        Field field2 = combatHelicopter.getClass().getDeclaredField("weight");
        field2.setAccessible(true);
        field2.set(combatHelicopter, -5700);

        AirLineService airLineService = AirLineServiceImpl.class.getConstructor().newInstance();

        Method andAirCraftMethod = AirLineServiceImpl.class.getDeclaredMethod("addAirCraft", AirCraft.class);
        andAirCraftMethod.invoke(airLineService, cargoAirPlane);
        andAirCraftMethod.invoke(airLineService, passengerAirPlane);
        andAirCraftMethod.invoke(airLineService, combatAirPlane);
        andAirCraftMethod.invoke(airLineService, civilHelicopter);
        andAirCraftMethod.invoke(airLineService, quadCopter);
        andAirCraftMethod.invoke(airLineService, quadCopterWithCamera);
        return airLineService;
    }

    private static void addAirCraftWithValidationError(AirLineService airLineService) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        CombatHelicopter combatHelicopter = CombatHelicopter.class.getConstructor(int.class, int.class, int.class,
                int.class, int.class, int.class, int.class).newInstance(-9, 3000, -600, 15, 13, 3, 300);
        Method andAirCraftMethod = AirLineServiceImpl.class.getDeclaredMethod("addAirCraft", AirCraft.class);
        try {
            andAirCraftMethod.invoke(airLineService, combatHelicopter);
        } catch (InvocationTargetException | IllegalAccessException ex) {
            System.out.println("\nException:\n" + ex.getCause().getMessage());
        }
    }

    private static void addAirCraftThatAlreadyExist(AirLineService airLineService) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        CombatAirPlane combatAirPlane = CombatAirPlane.class.getConstructor(double.class, double.class, int.class,
                int.class, int.class, int.class, int.class).newInstance(12.3, 35.5, 60, 2000, 1200,
                1000, 900);
        Method andAirCraftMethod = AirLineServiceImpl.class.getDeclaredMethod("addAirCraft", AirCraft.class);
        try {
            andAirCraftMethod.invoke(airLineService, combatAirPlane);
        } catch (InvocationTargetException ex) {
            System.out.println("\nException:\n" + ex.getCause().getMessage());
        }
    }

    private static List<Class> getClasses() {
        return new ArrayList<Class>() {{
            add(AirLineServiceImpl.class);
            add(AirCraft.class);
            add(QuadCopter.class);
            add(QuadCopterWithCamera.class);
            add(Helicopter.class);
            add(CivilHelicopter.class);
            add(CombatHelicopter.class);
            add(AirPlane.class);
            add(CargoAirPlane.class);
            add(CombatAirPlane.class);
            add(PassengerAirPlane.class);
        }};
    }

    private static void printMetadataOfAllClasses() {
        getClasses().forEach(Main::printMetadata);
    }

    private static void printMetadata(Class clazz) {
        System.out.println("\nClass name: " + clazz.getSimpleName());
        System.out.println("Super class name: " + clazz.getSuperclass().getSimpleName());
        System.out.println("Implemented interfaces: " + Arrays.stream(clazz.getInterfaces()).map(Class::getSimpleName)
                .collect(Collectors.toList()));
        System.out.println("All fields: " + FieldUtils.getAllFieldsList(clazz).stream().map(Field::getName)
                .collect(Collectors.toList()));
        System.out.println("All methods: ");
        Arrays.stream(clazz.getMethods()).filter(method -> method.getDeclaringClass() != Object.class)
                .collect(Collectors.toList()).forEach(method -> System.out.println("\t" + method));
    }

    private static void processThisCodeSmellsAnnotations() {
        System.out.println("\nProcess ThisCodeSmell com.epam.cdp.maksim.katuranau.module2.annotation:");
        getClasses().forEach(ThisCodeSmellsProcessor::process);
    }

    private static void processProdCodeAnnotation() throws Exception {
        System.out.println("\nProcess ProdCode com.epam.cdp.maksim.katuranau.module2.annotation:");
        ProdCodeProcessor.process(ProdRunnerImpl.class);
    }
}
