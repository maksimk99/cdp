package com.epam.cdp.maksim.katuranau.module8.task2.dao;

import com.epam.cdp.maksim.katuranau.module8.task2.model.CarModel;

import java.util.List;

public interface CarModelDao {

    List<CarModel> getCarModels();

    List<CarModel> getCarModelsOfCarMake(final Integer carMakeId);

    CarModel getCarModel(final int id);

    Integer addCarModel(final CarModel carModel);

    boolean updateCarModel(final CarModel carModel);

    boolean deleteCarModel(final int id);
}
