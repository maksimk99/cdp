package com.epam.cdp.maksim.katuranau.module8.task2.dao.stored.procedure;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.CarDao;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.impl.CarMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarDaoStoredProcedure implements CarDao {

    private static final Logger LOGGER = LogManager.getLogger(CarDaoStoredProcedure.class);

    private final MySqlConnector mySqlConnector;
    private final Properties properties;
    private final RowMapper<Car> carMapper;

    public CarDaoStoredProcedure(final MySqlConnector mySqlConnector, Properties properties) {
        this.properties = properties;
        LOGGER.debug("CarDaoStoredProcedure was created");
        this.mySqlConnector = mySqlConnector;
        this.carMapper = new CarMapper();
    }

    @Override
    public List<Car> getCars() {
        LOGGER.debug("method getCars");
        List<Car> carList = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("get.car.list.stored.procedure"))) {
            if (callableStatement.execute()) {
                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    carList.add(carMapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return carList;
    }

    @Override
    public Car getCar(final int id) {
        LOGGER.debug("method getCar with parameter: [{}]", id);
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("get.car.stored.procedure"))) {
            callableStatement.setInt(1, id);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            if (resultSet.next()) {
                return carMapper.mapRow(resultSet);
            } else {
                LOGGER.info("There is not car with id = " + id);
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Integer addCar(final Car car) {
        LOGGER.debug("method addCar with parameter: [{}]", car);
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("add.car.stored.procedure"))) {
            callableStatement.setInt(1, car.getYear());
            callableStatement.setInt(2, car.getMileage());
            callableStatement.setString(3, car.getFuelType());
            callableStatement.setInt(4, car.getCarModel().getCarModelId());
            callableStatement.registerOutParameter(5, Types.INTEGER);
            callableStatement.execute();
            return callableStatement.getInt(5);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public boolean updateCar(final Car car) {
        LOGGER.debug("method updateCar with parameter: [{}]", car);
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("update.car.stored.procedure"))) {
            callableStatement.setInt(1, car.getYear());
            callableStatement.setInt(2, car.getMileage());
            callableStatement.setString(3, car.getFuelType());
            callableStatement.setInt(4, car.getCarModel().getCarModelId());
            callableStatement.setInt(5, car.getCarId());
            return callableStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteCar(final int id) {
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("delete.car.stored.procedure"))) {
            callableStatement.setInt(1, id);
            return callableStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}

