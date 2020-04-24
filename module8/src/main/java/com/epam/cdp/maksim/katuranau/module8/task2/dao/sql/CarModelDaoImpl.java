package com.epam.cdp.maksim.katuranau.module8.task2.dao.sql;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.CarModelDao;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.impl.CarModelMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.model.CarModel;
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

public class CarModelDaoImpl implements CarModelDao {

    private static final Logger LOGGER = LogManager.getLogger(CarModelDaoImpl.class);

    private final MySqlConnector mySqlConnector;
    private final Properties properties;
    private final RowMapper<CarModel> carModelMapper;

    public CarModelDaoImpl(final MySqlConnector mySqlConnector, Properties properties) {
        this.mySqlConnector = mySqlConnector;
        this.properties = properties;
        this.carModelMapper = new CarModelMapper();
    }


    @Override
    public List<CarModel> getCarModels() {
        LOGGER.debug("method getCarModels");
        List<CarModel> carModelList = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("get.car.model.list.sql"));
            while (resultSet.next()) {
                carModelList.add(carModelMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return carModelList;
    }

    @Override
    public List<CarModel> getCarModelsOfCarMake(final Integer carMakeId) {
        LOGGER.debug("method getCarModelsOfCarMake");
        List<CarModel> carModelList = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("get.car.model.of.car.make.list.sql"))) {
            preparedStatement.setInt(1, carMakeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                carModelList.add(carModelMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return carModelList;
    }

    @Override
    public CarModel getCarModel(final int id) {
        LOGGER.debug("method getCarModel with carMakeId = {}", id);
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("get.car.model.sql"))) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return carModelMapper.mapRow(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Integer addCarModel(final CarModel carModel) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.car.model.sql"),
                        Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, carModel.getCarModel());
            preparedStatement.setInt(2, carModel.getCarMake().getCarMakeId());
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
    public boolean updateCarModel(final CarModel carModel) {
        LOGGER.debug("method updateCarModel with parameter: [{}]", carModel);
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("update.model.make.sql"),
                        Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, carModel.getCarModel());
            preparedStatement.setInt(2, carModel.getCarMake().getCarMakeId());
            preparedStatement.setInt(3, carModel.getCarModelId());
            preparedStatement.executeUpdate();
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteCarModel(final int id) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("delete.model.make.sql"))) {
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
