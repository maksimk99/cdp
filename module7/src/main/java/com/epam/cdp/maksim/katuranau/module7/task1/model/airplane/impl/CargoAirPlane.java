package com.epam.cdp.maksim.katuranau.module7.task1.model.airplane.impl;

import com.epam.cdp.maksim.katuranau.module7.task1.model.AirCraftType;
import com.epam.cdp.maksim.katuranau.module7.task1.model.airplane.AirPlane;

import java.util.Objects;

public class CargoAirPlane extends AirPlane {
    private double cargoHatchWidth;
    private double cargoHatchHeight;

    public CargoAirPlane() {
    }

    public CargoAirPlane(double wingSpan, double airPlaneLength, int numberOfPassengers, int carryingCapacity,
                         int rangeOfFlight, double cargoHatchWidth, double cargoHatchHeight) {
        super(wingSpan, airPlaneLength, numberOfPassengers, carryingCapacity, rangeOfFlight);
        this.cargoHatchWidth = cargoHatchWidth;
        this.cargoHatchHeight = cargoHatchHeight;
    }

    public double getCargoHatchWidth() {
        return cargoHatchWidth;
    }

    public CargoAirPlane setCargoHatchWidth(double cargoHatchWidth) {
        this.cargoHatchWidth = cargoHatchWidth;
        return this;
    }

    public double getCargoHatchHeight() {
        return cargoHatchHeight;
    }

    public CargoAirPlane setCargoHatchHeight(double cargoHatchHeight) {
        this.cargoHatchHeight = cargoHatchHeight;
        return this;
    }

    public static AirCraftType getAirCraftType() {
        return AirCraftType.CARGO_AIR_PLANE;
    }

    public boolean validate() {
        if (super.validate()) {
            return cargoHatchWidth > 0 && cargoHatchHeight > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CargoAirPlane)) return false;
        if (!super.equals(o)) return false;
        CargoAirPlane that = (CargoAirPlane) o;
        return Double.compare(that.cargoHatchWidth, cargoHatchWidth) == 0 &&
                Double.compare(that.cargoHatchHeight, cargoHatchHeight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargoHatchWidth, cargoHatchHeight);
    }

    @Override
    public String toString() {
        return "CargoAirPlane{"
                + "cargoHatchWidth=" + cargoHatchWidth
                + ", cargoHatchHeight=" + cargoHatchHeight
                + ", " + super.toString()
                + ", type=" + getAirCraftType()
                + "} ";
    }
}
