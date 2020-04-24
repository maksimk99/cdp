package com.epam.cdp.maksim.katuranau.module2.model.helicopter.impl;

import com.epam.cdp.maksim.katuranau.module2.annotation.UseStackOnly;
import com.epam.cdp.maksim.katuranau.module2.model.AirCraftType;
import com.epam.cdp.maksim.katuranau.module2.model.helicopter.Helicopter;

import java.math.BigDecimal;
import java.util.Objects;

public class CivilHelicopter extends Helicopter {
    @UseStackOnly
    private BigDecimal rentPrice;
    private int maxSpeed;

    public CivilHelicopter() {
    }

    public CivilHelicopter(int numberOfPassengers, int carryingCapacity, int rangeOfFlight, int rateOfClimb,
                           int screwDiameter, BigDecimal rentPrice, int maxSpeed) {
        super(numberOfPassengers, carryingCapacity, rangeOfFlight, rateOfClimb, screwDiameter);
        this.rentPrice = rentPrice.abs();
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public CivilHelicopter setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice.abs();
        return this;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public CivilHelicopter setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public static AirCraftType getAirCraftType() {
        return AirCraftType.CIVIL_HELICOPTER;
    }

    public boolean validate() {
        if (super.validate()) {
            return maxSpeed > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CivilHelicopter)) return false;
        if (!super.equals(o)) return false;
        CivilHelicopter that = (CivilHelicopter) o;
        return maxSpeed == that.maxSpeed &&
                rentPrice.equals(that.rentPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), rentPrice, maxSpeed);
    }

    @Override
    public String toString() {
        return "CivilHelicopter{"
                + "rentPrice=" + rentPrice
                + ", maxSpeed=" + maxSpeed
                + ", " + super.toString()
                + ", type=" + getAirCraftType()
                + "} ";
    }
}
