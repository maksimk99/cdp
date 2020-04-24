package com.epam.cdp.maksim.katuranau.module8.task2.model;

public class CarMake {
    private int carMakeId;
    private String carMake;

    public CarMake(final int carMakeId, final String carMake) {
        this.carMakeId = carMakeId;
        this.carMake = carMake;
    }

    public int getCarMakeId() {
        return carMakeId;
    }

    public void setCarMakeId(final int carMakeId) {
        this.carMakeId = carMakeId;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(final String carMake) {
        this.carMake = carMake;
    }

    @Override
    public final String toString() {
        return "carMake='" + carMake + '\'';
    }
}
