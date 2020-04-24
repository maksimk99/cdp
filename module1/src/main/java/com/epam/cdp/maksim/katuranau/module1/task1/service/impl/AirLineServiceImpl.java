package com.epam.cdp.maksim.katuranau.module1.task1.service.impl;

import com.epam.cdp.maksim.katuranau.module1.task1.exception.DuplicateException;
import com.epam.cdp.maksim.katuranau.module1.task1.exception.NotFoundException;
import com.epam.cdp.maksim.katuranau.module1.task1.exception.ValidationException;
import com.epam.cdp.maksim.katuranau.module1.task1.model.AirCraft;
import com.epam.cdp.maksim.katuranau.module1.task1.service.AirLineService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AirLineServiceImpl implements AirLineService {
    private ArrayList<AirCraft> listAirCrafts;

    public AirLineServiceImpl() {
        this.listAirCrafts = new ArrayList<>();
    }

    public AirLineServiceImpl(ArrayList<AirCraft> listAirCrafts) {
        this.listAirCrafts = listAirCrafts;
    }

    public AirCraft getAirCraft(int airCraftId) {
        try {
            return listAirCrafts.get(airCraftId - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new NotFoundException(airCraftId, listAirCrafts.size());
        }
    }

    public void addAirCraft(AirCraft airCraft) {
        if (airCraft != null && airCraft.validate()) {
            if (!listAirCrafts.contains(airCraft)) {
                listAirCrafts.add(airCraft);
            } else {
                throw new DuplicateException("AirLine already has this AirCraft:\n" + airCraft);
            }
        } else {
            throw new ValidationException("air craft has validation error:\n" + airCraft);
        }
    }

    public List<AirCraft> sortByRangeOfFlight() {
        //System.out.println("\nSort by range of flight:");
        return listAirCrafts.stream().sorted(Comparator.comparing(AirCraft::getRangeOfFlight))
                .collect(Collectors.toList());
    }

    public int calculateTotalCapacity() {
        //System.out.println("\nTotal capacity: " +
        return listAirCrafts.stream().mapToInt(AirCraft::getCarryingCapacity).sum();
    }

    public int calculateTotalNumberOfPassengers() {
        //System.out.println("\nTotal number of passengers: " +
        return listAirCrafts.stream().mapToInt(AirCraft::getNumberOfPassengers).sum();
    }

    public List<AirCraft> findAirCraftByParameters(int numberOfPassengersFrom, int numberOfPassengersTo,
                                                   int carryingCapacityFrom, int carryingCapacityTo,
                                                   int rangeOfFlightFrom, int rangeOfFlightTo) {
        return listAirCrafts.stream().filter(airCraft -> airCraft.getNumberOfPassengers() >= numberOfPassengersFrom
                && airCraft.getNumberOfPassengers() <= numberOfPassengersTo
                && airCraft.getCarryingCapacity() >= carryingCapacityFrom
                && airCraft.getCarryingCapacity() <= carryingCapacityTo
                && airCraft.getRangeOfFlight() >= rangeOfFlightFrom
                && airCraft.getRangeOfFlight() <= rangeOfFlightTo)
                .collect(Collectors.toList());
    }
}
