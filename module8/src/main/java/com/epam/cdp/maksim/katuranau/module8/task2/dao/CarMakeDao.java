package com.epam.cdp.maksim.katuranau.module8.task2.dao;

import com.epam.cdp.maksim.katuranau.module8.task2.model.CarMake;

import java.util.List;

public interface CarMakeDao {

    List<CarMake> getCarMakes();

    CarMake getCarMake(int id);

    Integer addCarMake(final CarMake carMake);

    boolean updateCarMake(final CarMake carMake);

    boolean deleteCarMake(final int id);
}
