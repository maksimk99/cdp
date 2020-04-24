package com.epam.cdp.maksim.katuranau.module1.task1.service;

import com.epam.cdp.maksim.katuranau.module1.task1.model.AirCraft;

import java.util.List;

public interface AirLineService {

    AirCraft getAirCraft(int airCraftId);

    void addAirCraft(AirCraft airCraft);

    List<AirCraft> sortByRangeOfFlight();

    int calculateTotalCapacity();

    int calculateTotalNumberOfPassengers();

    List<AirCraft> findAirCraftByParameters(int numberOfPassengersFrom, int numberOfPassengersTo, int carryingCapacityFrom,
                                  int carryingCapacityTo, int rangeOfFlightFrom, int rangeOfFlightTo);
}
