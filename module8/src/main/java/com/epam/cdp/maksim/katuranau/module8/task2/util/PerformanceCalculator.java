package com.epam.cdp.maksim.katuranau.module8.task2.util;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.CarDao;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.CarMakeDao;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.CarModelDao;
import com.epam.cdp.maksim.katuranau.module8.task2.model.Car;
import com.epam.cdp.maksim.katuranau.module8.task2.model.CarMake;
import com.epam.cdp.maksim.katuranau.module8.task2.model.CarModel;

public class PerformanceCalculator {

    private CarDao carDao;
    private CarMakeDao carMakeDao;
    private CarModelDao carModelDao;

    public PerformanceCalculator(CarDao carDao, CarMakeDao carMakeDao, CarModelDao carModelDao) {
        this.carDao = carDao;
        this.carMakeDao = carMakeDao;
        this.carModelDao = carModelDao;
    }

    public long logTimings() {
        long startTime = System.nanoTime();
        executeTasks();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private void executeTasks() {
        System.out.println("\nCount of car make = " + carMakeDao.getCarMakes().size());
        System.out.println("\nCount of car model = " + carModelDao.getCarModels().size());
        System.out.println("\nCount of car model of " + carMakeDao.getCarMake(1).getCarMake()
                + " = " + carModelDao.getCarModelsOfCarMake(1).size());
        System.out.println("\nCount of car = " + carDao.getCars().size());

        int carMakeId = carMakeDao.addCarMake(new CarMake(1, "Reno"));
        int carModelId = carModelDao.addCarModel(new CarModel(1, carMakeDao.getCarMake(carMakeId),
                "Logan"));
        int carId = carDao.addCar(new Car(1)
                .setYear(2018)
                .setMileage(12000)
                .setFuelType("Bensin")
                .setCarModel(carModelDao.getCarModel(carModelId))
        );
        System.out.println("\nGenerated car: " + carDao.getCar(carId));

        CarMake carMake = carMakeDao.getCarMake(carMakeId);
        carMake.setCarMake("Honda");
        carMakeDao.updateCarMake(carMake);
        CarModel carModel = carModelDao.getCarModel(carModelId);
        carModel.setCarModel("Accord");
        carModelDao.updateCarModel(carModel);
        Car car = carDao.getCar(carId)
                .setYear(2017)
                .setMileage(120000)
                .setFuelType("Diesel");
        carDao.updateCar(car);
        System.out.println("\nUpdated car: " + carDao.getCar(carId));

        carDao.deleteCar(carId);
        carModelDao.deleteCarModel(carModelId);
        carMakeDao.deleteCarMake(carMakeId);
    }
}
