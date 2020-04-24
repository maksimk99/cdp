package com.epam.cdp.maksim.katuranau.module1.task1.model.helicopter.impl;

import com.epam.cdp.maksim.katuranau.module1.task1.model.AirCraftType;
import com.epam.cdp.maksim.katuranau.module1.task1.model.helicopter.Helicopter;

import java.util.Objects;

public class CombatHelicopter extends Helicopter {
    private int crew;
    private int weight;

    public CombatHelicopter() {
    }

    public CombatHelicopter(int numberOfPassengers, int carryingCapacity, int rangeOfFlight, int rateOfClimb,
                            int screwDiameter, int crew, int weight) {
        super(numberOfPassengers, carryingCapacity, rangeOfFlight, rateOfClimb, screwDiameter);
        this.crew = crew;
        this.weight = weight;
    }

    public int getCrew() {
        return crew;
    }

    public CombatHelicopter setCrew(int crew) {
        this.crew = crew;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public CombatHelicopter setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public static AirCraftType getAirCraftType() {
        return AirCraftType.COMBAT_HELICOPTER;
    }

    public boolean validate() {
        if (super.validate()) {
            return crew > 0 && weight > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CombatHelicopter)) return false;
        if (!super.equals(o)) return false;
        CombatHelicopter that = (CombatHelicopter) o;
        return crew == that.crew &&
                weight == that.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), crew, weight);
    }

    @Override
    public String toString() {
        return "CombatHelicopter{"
                + "crew=" + crew
                + ", wight=" + weight
                + ", " + super.toString()
                + ", type=" + getAirCraftType()
                + "} ";
    }
}