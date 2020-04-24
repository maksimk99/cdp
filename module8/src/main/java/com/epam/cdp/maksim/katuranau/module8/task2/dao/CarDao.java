package com.epam.cdp.maksim.katuranau.module8.task2.dao;

import com.epam.cdp.maksim.katuranau.module8.task2.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> getCars();

    Car getCar(int id);

    Integer addCar(Car car);

    boolean updateCar(Car car);

    boolean deleteCar(int id);
}
