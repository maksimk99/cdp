package com.epam.cdp.maksim.katuranau.module8.task2.model;

public class Car {
    private int carId;
    private int year;
    private int mileage;
    private String fuelType;
    private CarModel carModel;

    public Car(final int carId) {
        this.carId = carId;
    }

    public int getCarId() {
        return carId;
    }

    public Car setCarId(final int carId) {
        this.carId = carId;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Car setYear(final int year) {
        this.year = year;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public Car setMileage(final int mileage) {
        this.mileage = mileage;
        return this;
    }

    public String getFuelType() {
        return fuelType;
    }

    public Car setFuelType(final String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public Car setCarModel(CarModel carModel) {
        this.carModel = carModel;
        return this;
    }

    @Override
    public final String toString() {
        return "carId=" + carId
                + ", year=" + year
                + ", mileage=" + mileage
                + ", " + fuelType
                + ", " + carModel
                + ';';
    }
}
