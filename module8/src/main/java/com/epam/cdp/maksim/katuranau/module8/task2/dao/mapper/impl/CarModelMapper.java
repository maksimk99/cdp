package com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.impl;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.model.CarModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarModelMapper implements RowMapper<CarModel> {

    private static final Logger LOGGER = LogManager.getLogger(CarModelMapper.class);

    private static final String CAR_MODEL_ID = "car_model_id";
    private static final String CAR_MODEL = "car_model";

    private CarMakeMapper carMakeMapper;

    public CarModelMapper() {
        carMakeMapper = new CarMakeMapper();
    }

    @Override
    public CarModel mapRow(final ResultSet resultSet) throws SQLException {
        CarModel carModel = new CarModel(resultSet.getInt(CAR_MODEL_ID), carMakeMapper.mapRow(resultSet),
                resultSet.getString(CAR_MODEL));
        LOGGER.debug("row ({}, {}) has been mapped", carModel.getCarModelId(), carModel.getCarModel());
        return carModel;
    }
}
