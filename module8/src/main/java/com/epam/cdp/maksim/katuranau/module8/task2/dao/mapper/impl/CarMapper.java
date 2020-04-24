package com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.impl;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper implements RowMapper<Car> {

    private static final Logger LOGGER = LogManager.getLogger(CarMapper.class);

    private static final String CAR_ID = "car_id";
    private static final String YEAR = "year";
    private static final String MILEAGE = "mileage";
    private static final String FUEL_TYPE = "fuel_type";

    private CarModelMapper carModelMapper;

    public CarMapper() {
        this.carModelMapper = new CarModelMapper();
    }

    @Override
    public Car mapRow(final ResultSet resultSet) throws SQLException {
        Car car = new Car(resultSet.getInt(CAR_ID))
                .setYear(resultSet.getInt(YEAR))
                .setMileage(resultSet.getInt(MILEAGE))
                .setFuelType(resultSet.getString(FUEL_TYPE))
                .setCarModel(carModelMapper.mapRow(resultSet));
        LOGGER.debug("row ({}, {}, {}) has been mapped", car.getCarId(), car.getYear(), car.getMileage());
        return car;
    }
}
