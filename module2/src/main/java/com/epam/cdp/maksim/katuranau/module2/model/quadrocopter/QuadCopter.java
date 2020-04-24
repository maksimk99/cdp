package com.epam.cdp.maksim.katuranau.module2.model.quadrocopter;

import com.epam.cdp.maksim.katuranau.module2.model.AirCraft;
import com.epam.cdp.maksim.katuranau.module2.model.AirCraftType;

import java.util.Objects;

public class QuadCopter extends AirCraft {
    private int numberOfBlades;
    private int maxFlightTime;
    private boolean isGpsExist;
    private double weight;


    public QuadCopter() {
    }

    public QuadCopter(int carryingCapacity, int rangeOfFlight, int numberOfBlades,
                      int maxFlightTime, boolean isGpsExist, double weight) {
        super(0, carryingCapacity, rangeOfFlight);
        this.numberOfBlades = numberOfBlades;
        this.maxFlightTime = maxFlightTime;
        this.isGpsExist = isGpsExist;
        this.weight = weight;
    }

    public int getNumberOfBlades() {
        return numberOfBlades;
    }

    public QuadCopter setNumberOfBlades(int numberOfBlades) {
        this.numberOfBlades = numberOfBlades;
        return this;
    }

    public double getWeight() {
        return weight;
    }

    public QuadCopter setWeight(double weight) {
        this.weight = weight;
        return this;
    }

    public int getMaxFlightTime() {
        return maxFlightTime;
    }

    public QuadCopter setMaxFlightTime(int maxFlightTime) {
        this.maxFlightTime = maxFlightTime;
        return this;
    }

    public boolean isGpsExist() {
        return isGpsExist;
    }

    public QuadCopter setGpsExist(boolean gpsExist) {
        isGpsExist = gpsExist;
        return this;
    }

    public static AirCraftType getAirCraftType() {
        return AirCraftType.QUAD_COPTER;
    }

    public boolean validate() {
        if (super.validate()) {
            return numberOfBlades > 0 && maxFlightTime > 0 && weight > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuadCopter)) return false;
        if (!super.equals(o)) return false;
        QuadCopter that = (QuadCopter) o;
        return numberOfBlades == that.numberOfBlades &&
                maxFlightTime == that.maxFlightTime &&
                isGpsExist == that.isGpsExist &&
                Double.compare(that.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfBlades, maxFlightTime, isGpsExist, weight);
    }

    @Override
    public String toString() {
        return "QuadCopter{"
                + "numberOfBlades=" + numberOfBlades
                + ", maxFlightTime=" + maxFlightTime
                + ", isGpsExist=" + isGpsExist
                + ", " + super.toString()
                + ", type=" + getAirCraftType()
                + "} ";
    }
}
