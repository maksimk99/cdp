package com.epam.cdp.maksim.katuranau.module8.task2.dao.sql;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.CarDao;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.impl.CarMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CarDaoImpl implements CarDao {

    private static final Logger LOGGER = LogManager.getLogger(CarDaoImpl.class);

    private final MySqlConnector mySqlConnector;
    private final Properties properties;
    private final RowMapper<Car> carMapper;

    public CarDaoImpl(final MySqlConnector mySqlConnector, Properties properties) {
        this.mySqlConnector = mySqlConnector;
        this.properties = properties;
        this.carMapper = new CarMapper();
    }

    @Override
    public List<Car> getCars() {
        LOGGER.debug("method getCars");
        List<Car> carList = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("get.car.list.sql"));
            while (resultSet.next()) {
                carList.add(carMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return carList;
    }

    @Override
    public Car getCar(final int id) {
        LOGGER.debug("method getCar with parameter: [{}]", id);
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("get.car.sql"))) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return carMapper.mapRow(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Integer addCar(final Car car) {
        LOGGER.debug("method addCar with parameter: [{}]", car);
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.car.sql"),
                        Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, car.getYear());
            preparedStatement.setInt(2, car.getMileage());
            preparedStatement.setString(3, car.getFuelType());
            preparedStatement.setInt(4, car.getCarModel().getCarModelId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public boolean updateCar(final Car car) {
        LOGGER.debug("method updateCar with parameter: [{}]", car);
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("update.car.sql"),
                        Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, car.getYear());
            preparedStatement.setInt(2, car.getMileage());
            preparedStatement.setString(3, car.getFuelType());
            preparedStatement.setInt(4, car.getCarModel().getCarModelId());
            preparedStatement.setInt(5, car.getCarId());
            preparedStatement.executeUpdate();
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteCar(final int id) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("delete.car.sql"))) {
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
