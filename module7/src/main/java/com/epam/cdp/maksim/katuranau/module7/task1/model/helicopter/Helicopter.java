package com.epam.cdp.maksim.katuranau.module7.task1.model.helicopter;

import com.epam.cdp.maksim.katuranau.module7.task1.model.AirCraft;

import java.util.Objects;

public abstract class Helicopter extends AirCraft {
    private transient int rateOfClimb;
    private transient int screwDiameter;

    protected Helicopter() {
    }

    protected Helicopter(int numberOfPassengers, int carryingCapacity, int rangeOfFlight,
                         int rateOfClimb, int screwDiameter) {
        super(numberOfPassengers, carryingCapacity, rangeOfFlight);
        this.rateOfClimb = rateOfClimb;
        this.screwDiameter = screwDiameter;
    }

    public final int getRateOfClimb() {
        return rateOfClimb;
    }

    public final Helicopter setRateOfClimb(int rateOfClimb) {
        this.rateOfClimb = rateOfClimb;
        return this;
    }

    public final int getScrewDiameter() {
        return screwDiameter;
    }

    public final Helicopter setScrewDiameter(int screwDiameter) {
        this.screwDiameter = screwDiameter;
        return this;
    }

    public boolean validate() {
        if (super.validate()) {
            return rateOfClimb > 0 && screwDiameter > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Helicopter)) return false;
        if (!super.equals(o)) return false;
        Helicopter that = (Helicopter) o;
        return rateOfClimb == that.rateOfClimb &&
                screwDiameter == that.screwDiameter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rateOfClimb, screwDiameter);
    }

    @Override
    public String toString() {
        return "rateOfClimb=" + rateOfClimb +
                ", screwDiameter=" + screwDiameter +
                ", " + super.toString();
    }
}