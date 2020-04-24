package com.epam.cdp.maksim.katuranau.module2.service.impl;

import com.epam.cdp.maksim.katuranau.module2.annotation.ThisCodeSmell;
import com.epam.cdp.maksim.katuranau.module2.annotation.UseArrayList;
import com.epam.cdp.maksim.katuranau.module2.exception.DuplicateException;
import com.epam.cdp.maksim.katuranau.module2.exception.NotFoundException;
import com.epam.cdp.maksim.katuranau.module2.exception.ValidationException;
import com.epam.cdp.maksim.katuranau.module2.model.AirCraft;
import com.epam.cdp.maksim.katuranau.module2.service.AirLineService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ThisCodeSmell(reviewer = "Petya")
@ThisCodeSmell(reviewer = "Andrei")
@ThisCodeSmell(reviewer = "Vasya")
public class AirLineServiceImpl implements AirLineService {
    @ThisCodeSmell(reviewer = "Vasya")
    private ArrayList<AirCraft> listAirCrafts;

    public AirLineServiceImpl() {
        this.listAirCrafts = new ArrayList<>();
    }

    public AirLineServiceImpl(ArrayList<AirCraft> listAirCrafts) {
        this.listAirCrafts = listAirCrafts;
    }

    @Override
    public AirCraft getAirCraft(int airCraftId) {
        try {
            return listAirCrafts.get(airCraftId - 1);
        } catch (IndexOutOfBoundsException ex) {
            throw new NotFoundException(airCraftId, listAirCrafts.size());
        }
    }

    @ThisCodeSmell(reviewer = "Andrei")
    @ThisCodeSmell(reviewer = "Vasya")
    @Override
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

    @UseArrayList
    @Override
    public List<AirCraft> sortByRangeOfFlight() {
        return listAirCrafts.stream().sorted(Comparator.comparing(AirCraft::getRangeOfFlight))
                .collect(Collectors.toList());
    }

    @Override
    public int calculateTotalCapacity() {
        return listAirCrafts.stream().mapToInt(AirCraft::getCarryingCapacity).sum();
    }

    @Override
    public int calculateTotalNumberOfPassengers() {
        return listAirCrafts.stream().mapToInt(AirCraft::getNumberOfPassengers).sum();
    }

    @UseArrayList
    @Override
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
