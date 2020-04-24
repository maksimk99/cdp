package com.epam.cdp.maksim.katuranau.module2.model.airplane.impl;

import com.epam.cdp.maksim.katuranau.module2.annotation.UseStackOnly;
import com.epam.cdp.maksim.katuranau.module2.model.AirCraftType;
import com.epam.cdp.maksim.katuranau.module2.model.airplane.AirPlane;

import java.math.BigDecimal;
import java.util.Objects;

public class PassengerAirPlane extends AirPlane {
    private double fuselageWidth;
    @UseStackOnly
    private BigDecimal ticketPrice;

    public PassengerAirPlane() {
    }

    public PassengerAirPlane(double wingSpan, double airPlaneLength, int numberOfPassengers,
                             int carryingCapacity, int rangeOfFlight, double fuselageWidth, BigDecimal ticketPrice) {
        super(wingSpan, airPlaneLength, numberOfPassengers, carryingCapacity, rangeOfFlight);
        this.fuselageWidth = fuselageWidth;
        this.ticketPrice = ticketPrice.abs();
    }

    public double getFuselageWidth() {
        return fuselageWidth;
    }

    public PassengerAirPlane setFuselageWidth(double fuselageWidth) {
        this.fuselageWidth = fuselageWidth;
        return this;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public PassengerAirPlane setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice.abs();
        return this;
    }

    public static AirCraftType getAirCraftType() {
        return AirCraftType.PASSENGER_AIR_PLANE;
    }

    public boolean validate() {
        if (super.validate()) {
            return fuselageWidth > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerAirPlane)) return false;
        if (!super.equals(o)) return false;
        PassengerAirPlane that = (PassengerAirPlane) o;
        return Double.compare(that.fuselageWidth, fuselageWidth) == 0 &&
                ticketPrice.equals(that.ticketPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fuselageWidth, ticketPrice);
    }

    @Override
    public String toString() {
        return "PassengerAirPlane{"
                + "fuselageWidth=" + fuselageWidth
                + ", ticketPrice=" + ticketPrice
                + ", " + super.toString()
                + ", type=" + getAirCraftType()
                + "} ";
    }
}
