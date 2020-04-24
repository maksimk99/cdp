package com.epam.cdp.maksim.katuranau.module1.task1.model.airplane;

import com.epam.cdp.maksim.katuranau.module1.task1.model.AirCraft;

import java.util.Objects;

public abstract class AirPlane extends AirCraft {
    private double wingSpan;
    private double airPlaneLength;

    protected AirPlane() {
    }

    protected AirPlane(double wingSpan, double airPlaneLength, int numberOfPassengers, int carryingCapacity,
                       int rangeOfFlight) {
        super(numberOfPassengers, carryingCapacity, rangeOfFlight);
        this.wingSpan = wingSpan;
        this.airPlaneLength = airPlaneLength;
    }

    public final double getWingSpan() {
        return wingSpan;
    }

    public final AirPlane setWingSpan(double wingSpan) {
        this.wingSpan = wingSpan;
        return this;
    }

    public final double getAirPlaneLength() {
        return airPlaneLength;
    }

    public final AirPlane setAirPlaneLength(double airPlaneLength) {
        this.airPlaneLength = airPlaneLength;
        return this;
    }

    public boolean validate() {
        if (super.validate()) {
            return wingSpan > 0 && airPlaneLength > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AirPlane)) return false;
        if (!super.equals(o)) return false;
        AirPlane airPlane = (AirPlane) o;
        return Double.compare(airPlane.wingSpan, wingSpan) == 0 &&
                Double.compare(airPlane.airPlaneLength, airPlaneLength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wingSpan, airPlaneLength);
    }

    @Override
    public String toString() {
        return "wingSpan=" + wingSpan +
                ", airPlaneLength=" + airPlaneLength +
                ", " + super.toString();
    }
}
