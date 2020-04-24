package com.epam.cdp.maksim.katuranau.module8.task2.dao.stored.procedure;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.CarMakeDao;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper.impl.CarMakeMapper;
import com.epam.cdp.maksim.katuranau.module8.task2.model.CarMake;
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

public class CarMakeDaoStoredProcedure implements CarMakeDao {

    private static final Logger LOGGER = LogManager.getLogger(CarMakeDaoStoredProcedure.class);

    private final MySqlConnector mySqlConnector;
    private final Properties properties;
    private final RowMapper<CarMake> carMakeMapper;

    public CarMakeDaoStoredProcedure(final MySqlConnector mySqlConnector, Properties properties) {
        this.properties = properties;
        this.carMakeMapper = new CarMakeMapper();
        this.mySqlConnector = mySqlConnector;
    }

    @Override
    public List<CarMake> getCarMakes() {
        LOGGER.debug("method getCarMakes");
        List<CarMake> carMakeList = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("get.car.make.list.stored.procedure"))) {
            if (callableStatement.execute()) {
                ResultSet resultSet = callableStatement.getResultSet();
                while (resultSet.next()) {
                    carMakeList.add(carMakeMapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return carMakeList;
    }

    @Override
    public CarMake getCarMake(final int id) {
        LOGGER.debug("method getCarMake with carMakeId = {}", id);
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("get.car.make.stored.procedure"))) {
            callableStatement.setInt(1, id);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            if (resultSet.next()) {
                return carMakeMapper.mapRow(resultSet);
            } else {
                LOGGER.info("There is not car make with id = " + id);
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Integer addCarMake(final CarMake carMake) {
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("add.car.make.stored.procedure"))) {
            callableStatement.setString(1, carMake.getCarMake());
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.execute();
            return callableStatement.getInt(2);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public boolean updateCarMake(final CarMake carMake) {
        LOGGER.debug("method updateCarMake with parameter: [{}]", carMake);
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("update.car.make.stored.procedure"))) {
            callableStatement.setInt(1, carMake.getCarMakeId());
            callableStatement.setString(2, carMake.getCarMake());
            return callableStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deleteCarMake(final int id) {
        try (Connection connection = mySqlConnector.getConnection(); CallableStatement callableStatement =
                connection.prepareCall(properties.getProperty("delete.car.make.stored.procedure"))) {
            callableStatement.setInt(1, id);
            return callableStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }
}