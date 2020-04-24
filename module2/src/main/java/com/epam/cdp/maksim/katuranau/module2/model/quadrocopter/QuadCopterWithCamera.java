package com.epam.cdp.maksim.katuranau.module2.model.quadrocopter;

import com.epam.cdp.maksim.katuranau.module2.model.AirCraftType;

import java.util.Objects;

public class QuadCopterWithCamera extends QuadCopter {
    private double megapixelCount;

    public QuadCopterWithCamera() {
    }

    public QuadCopterWithCamera(int carryingCapacity, int rangeOfFlight, int numberOfBlades,
                                int maxFlightTime, boolean isGpsExist, double weight, double megapixelCount) {
        super(carryingCapacity, rangeOfFlight, numberOfBlades, maxFlightTime, isGpsExist, weight);
        this.megapixelCount = megapixelCount;
    }

    public double getMegapixelCount() {
        return megapixelCount;
    }

    public QuadCopterWithCamera setMegapixelCount(double megapixelCount) {
        this.megapixelCount = megapixelCount;
        return this;
    }

    public static AirCraftType getAirCraftType() {
        return AirCraftType.QUAD_COPTER_WITH_CAMERA;
    }

    public boolean validate() {
        if (super.validate()) {
            return megapixelCount > 0;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuadCopterWithCamera)) return false;
        if (!super.equals(o)) return false;
        QuadCopterWithCamera that = (QuadCopterWithCamera) o;
        return Double.compare(that.megapixelCount, megapixelCount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), megapixelCount);
    }

    @Override
    public String toString() {
        return "QuadCopterWithCamera{"
                + "camera=" + megapixelCount
                + ", " + super.toString()
                + "}";
    }
}
