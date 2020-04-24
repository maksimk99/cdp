package com.epam.cdp.maksim.katuranau.module1.task1.model.airplane.impl;

import com.epam.cdp.maksim.katuranau.module1.task1.model.AirCraftType;
import com.epam.cdp.maksim.katuranau.module1.task1.model.airplane.AirPlane;

import java.util.Objects;

public class CombatAirPlane extends AirPlane {
    private int combatRadius;
    private int maxSpeed;

    public CombatAirPlane() {
    }

    public CombatAirPlane(double wingSpan, double airPlaneLength, int numberOfPassengers, int carryingCapacity,
                          int rangeOfFlight, int combatRadius, int maxSpeed) {
        super(wingSpan, airPlaneLength, numberOfPassengers, carryingCapacity, rangeOfFlight);
        this.combatRadius = combatRadius;
        this.maxSpeed = maxSpeed;
    }

    public int getCombatRadius() {
        return combatRadius;
    }

    public CombatAirPlane setCombatRadius(int combatRadius) {
        this.combatRadius = combatRadius;
        return this;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public CombatAirPlane setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public static AirCraftType getAirCraftType() {
        return AirCraftType.COMBAT_AIR_PLANE;
    }

    public boolean validate() {
        if (super.validate()) {
            return combatRadius > 0 && maxSpeed > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CombatAirPlane)) return false;
        if (!super.equals(o)) return false;
        CombatAirPlane that = (CombatAirPlane) o;
        return combatRadius == that.combatRadius &&
                maxSpeed == that.maxSpeed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), combatRadius, maxSpeed);
    }

    @Override
    public String toString() {
        return "CombatAirPlane{"
                + "combatRadius=" + combatRadius
                + ", maxSpeed=" + maxSpeed
                + ", " + super.toString()
                + ", type=" + getAirCraftType()
                + "} ";
    }
}
