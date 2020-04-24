package com.epam.cdp.maksim.katuranau.module2.service;

import com.epam.cdp.maksim.katuranau.module2.model.AirCraft;

import java.util.List;

public interface AirLineService {

    AirCraft getAirCraft(int airCraftId);

    void addAirCraft(AirCraft airCraft); //Validation com.epam.cdp.maksim.katuranau.module2.exception, Already exist com.epam.cdp.maksim.katuranau.module2.exception

    List<AirCraft> sortByRangeOfFlight();

    int calculateTotalCapacity();

    int calculateTotalNumberOfPassengers();

    List<AirCraft> findAirCraftByParameters(int numberOfPassengersFrom, int numberOfPassengersTo, int carryingCapacityFrom,
                                            int carryingCapacityTo, int rangeOfFlightFrom, int rangeOfFlightTo); //Not found com.epam.cdp.maksim.katuranau.module2.exception
}
