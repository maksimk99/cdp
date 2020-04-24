package com.epam.cdp.maksim.katuranau.module8.task2.dao.sql;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.CarMakeDao;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.impl.CarMakeMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.model.CarMake;
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

public class CarMakeDaoImpl implements CarMakeDao {

    private static final Logger LOGGER = LogManager.getLogger(CarMakeDaoImpl.class);

    private final MySqlConnector mySqlConnector;
    private final Properties properties;
    private final RowMapper<CarMake> carMakeMapper;

    public CarMakeDaoImpl(final MySqlConnector mySqlConnector, Properties properties) {
        this.mySqlConnector = mySqlConnector;
        this.properties = properties;
        this.carMakeMapper = new CarMakeMapper();
    }

    @Override
    public List<CarMake> getCarMakes() {
        LOGGER.debug("method getCarMakes");
        List<CarMake> carMakeList = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("get.car.make.list.sql"));
            while (resultSet.next()) {
                carMakeList.add(carMakeMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return carMakeList;
    }

    @Override
    public CarMake getCarMake(final int id) {
        LOGGER.debug("method getCarMake with carMakeId = {}", id);
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("get.car.make.sql"))) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return carMakeMapper.mapRow(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Integer addCarMake(final CarMake carMake) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.car.make.sql"),
                        Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, carMake.getCarMake());
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
    public boolean updateCarMake(final CarMake carMake) {
        LOGGER.debug("method updateCarMake with parameter: [{}]", carMake);
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("update.car.make.sql"),
                        Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, carMake.getCarMake());
            preparedStatement.setInt(2, carMake.getCarMakeId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteCarMake(final int id) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("delete.car.make.sql"))) {
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}