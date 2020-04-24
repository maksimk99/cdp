package com.epam.cdp.maksim.katuranau.module8.task2.model;

public class CarModel {
    private int carModelId;
    private CarMake carMake;
    private String carModel;

    public CarModel(final int carModelId, final CarMake carMake, final String carModel) {
        this.carModelId = carModelId;
        this.carMake = carMake;
        this.carModel = carModel;
    }

    public int getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(final int carModelId) {
        this.carModelId = carModelId;
    }

    public CarMake getCarMake() {
        return carMake;
    }

    public void setCarMake(final CarMake carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(final String carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return carMake +
               ", carModel='" + carModel + '\'';
    }
}
