package com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.impl;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.model.CarMake;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMakeMapper implements RowMapper<CarMake> {

    private static final Logger LOGGER = LogManager.getLogger(CarMakeMapper.class);

    private static final String CAR_MAKE_ID = "car_make_id";
    private static final String CAR_MAKE = "car_make";

    @Override
    public CarMake mapRow(final ResultSet resultSet) throws SQLException {
        CarMake carMake = new CarMake(resultSet.getInt(CAR_MAKE_ID), resultSet.getString(CAR_MAKE));
        LOGGER.debug("row ({}, {}) has been mapped", carMake.getCarMakeId(), carMake.getCarMake());
        return carMake;
    }
}
