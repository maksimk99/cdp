package com.epam.cdp.maksim.katuranau.module1.task1.model;

import java.util.Objects;

public abstract class AirCraft {
    private int numberOfPassengers;
    private int carryingCapacity;
    private int rangeOfFlight;

    protected AirCraft() {
    }

    protected AirCraft(int numberOfPassengers, int carryingCapacity, int rangeOfFlight) {
        this.numberOfPassengers = numberOfPassengers;
        this.carryingCapacity = carryingCapacity;
        this.rangeOfFlight = rangeOfFlight;
    }

    public final int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public AirCraft setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
        return this;
    }

    public final int getCarryingCapacity() {
        return carryingCapacity;
    }

    public AirCraft setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        return this;
    }

    public final int getRangeOfFlight() {
        return rangeOfFlight;
    }

    public AirCraft setRangeOfFlight(int rangeOfFlight) {
        this.rangeOfFlight = rangeOfFlight;
        return this;
    }

    public boolean validate() {
        return numberOfPassengers >= 0 && carryingCapacity > 0 && rangeOfFlight > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirCraft)) return false;
        AirCraft airCraft = (AirCraft) o;
        return numberOfPassengers == airCraft.numberOfPassengers &&
                carryingCapacity == airCraft.carryingCapacity &&
                rangeOfFlight == airCraft.rangeOfFlight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPassengers, carryingCapacity, rangeOfFlight);
    }

    @Override
    public String toString() {
        return "numberOfPassengers=" + numberOfPassengers
                + ", carryingCapacity=" + carryingCapacity
                + ", rangeOfFlight=" + rangeOfFlight;
    }
}
