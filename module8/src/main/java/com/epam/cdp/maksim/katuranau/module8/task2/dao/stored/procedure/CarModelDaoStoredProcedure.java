package com.epam.cdp.maksim.katuranau.module8.task2.dao.stored.procedure;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.CarModelDao;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.impl.CarModelMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.model.CarModel;
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

public class CarModelDaoStoredProcedure implements CarModelDao {

    private static final Logger LOGGER = LogManager.getLogger(CarModelDaoStoredProcedure.class);

    private final MySqlConnector mySqlConnector;
    private final Properties properties;
    private final RowMapper<CarModel> carModelMapper;

    public CarModelDaoStoredProcedure(final MySqlConnector mySqlConnector, Properties properties) {
        this.properties = properties;
        this.carModelMapper = new CarModelMapper();
        this.mySqlConnector = mySqlConnector;
    }

    @Override
    public List<CarModel> getCarModels() {
        LOGGER.debug("method getCarModels");
        List<CarModel> carModelList = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("get.car.model.list.stored.procedure"))) {
            if (callableStatement.execute()) {
                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    carModelList.add(carModelMapper.mapRow(resultSet));
                }
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
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("get.car.model.of.car.make.list.stored.procedure"))) {
            callableStatement.setInt(1, carMakeId);
            if (callableStatement.execute()) {
                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    carModelList.add(carModelMapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return carModelList;
    }

    @Override
    public CarModel getCarModel(final int id) {
        LOGGER.debug("method getCarModel with carMakeId = {}", id);
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("get.car.model.stored.procedure"))) {
            callableStatement.setInt(1, id);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            if (resultSet.next()) {
                return carModelMapper.mapRow(resultSet);
            } else {
                LOGGER.info("There is not car model with id = " + id);
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Integer addCarModel(final CarModel carModel) {
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("add.car.model.stored.procedure"))) {
            callableStatement.setString(1, carModel.getCarModel());
            callableStatement.setInt(2, carModel.getCarMake().getCarMakeId());
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.execute();
            return callableStatement.getInt(3);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public boolean updateCarModel(final CarModel carModel) {
        LOGGER.debug("method updateCarModel with parameter: [{}]", carModel);
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("update.model.make.stored.procedure"))) {
            callableStatement.setString(1, carModel.getCarModel());
            callableStatement.setInt(2, carModel.getCarMake().getCarMakeId());
            callableStatement.setInt(3, carModel.getCarModelId());
            return callableStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteCarModel(final int id) {
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("delete.model.make.stored.procedure"))) {
            callableStatement.setInt(1, id);
            return callableStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}
