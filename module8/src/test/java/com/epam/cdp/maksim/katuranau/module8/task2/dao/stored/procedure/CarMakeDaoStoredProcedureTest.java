package com.epam.cdp.maksim.katuranau.module8.task2.dao.stored.procedure;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.CarMakeDao;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task2.model.CarMake;
import com.epam.cdp.maksim.katuranau.module8.task2.util.PropertyReader;
import com.epam.cdp.maksim.katuranau.module8.task2.util.StoredProcedure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;


public class CarMakeDaoStoredProcedureTest {

    private static final Logger LOGGER = LogManager.getLogger(CarMakeDaoStoredProcedureTest.class);

    private static MySqlConnector mySqlConnector;
    private static StoredProcedure storedProcedure;
    private static CarMakeDao carMakeDao;

    private static final String GET_LIST_CAR_MAKE_STORED_PROCEDURE_CREATE_SQL = "CREATE PROCEDURE `get_car_make_list`" +
            " () BEGIN SELECT * FROM car_make; END;";
    private static final String GET_CAR_MAKE_STORED_PROCEDURE_CREATE_SQL = "CREATE PROCEDURE `get_car_make` " +
            "(IN carMakeId INT) BEGIN SELECT cm.* FROM car_make cm WHERE cm.car_make_id = carMakeId; END;";
    private static final String CREATE_CAR_MAKE_STORED_PROCEDURE_CREATE_SQL = "CREATE PROCEDURE `create_car_make`" +
            " (IN name VARCHAR(45), OUT newCarMakeID INT) BEGIN INSERT INTO car_make (car_make) VALUES (name);" +
            " SET newCarMakeID = (SELECT c.car_make_id FROM car_make c WHERE c.car_make = name); END;";
    private static final String UPDATE_CAR_MAKE_STORED_PROCEDURE_CREATE_SQL = "CREATE PROCEDURE `update_car_make`" +
            " (IN carMakeId INT, name VARCHAR(45)) BEGIN UPDATE car_make SET car_make = name" +
            " WHERE car_make_id = carMakeId; END;";
    private static final String DELETE_CAR_MAKE_STORED_PROCEDURE_CREATE_SQL = "CREATE PROCEDURE `delete_car_make`" +
            " (IN carMakeId INT) BEGIN DELETE FROM car_make WHERE car_make_id = carMakeId; END;";

    @BeforeClass
    public static void setup() {
        Properties properties = PropertyReader.getProperties("task2/stored-procedure.properties");
        mySqlConnector = new MySqlConnector();
        storedProcedure = new StoredProcedure(mySqlConnector, properties);
        carMakeDao = new CarMakeDaoStoredProcedure(mySqlConnector, properties);
        storedProcedure.dropAllStoredProcedure();
        createStatement(GET_LIST_CAR_MAKE_STORED_PROCEDURE_CREATE_SQL);
        createStatement(GET_CAR_MAKE_STORED_PROCEDURE_CREATE_SQL);
        createStatement(CREATE_CAR_MAKE_STORED_PROCEDURE_CREATE_SQL);
        createStatement(UPDATE_CAR_MAKE_STORED_PROCEDURE_CREATE_SQL);
        createStatement(DELETE_CAR_MAKE_STORED_PROCEDURE_CREATE_SQL);
    }

    @AfterClass
    public static void createStoredProcedures() {
        storedProcedure.createStoredProcedures();
    }

    @Test
    public void getCarMakes() {
        assertNotNull(carMakeDao.getCarMakes());
        assertFalse(carMakeDao.getCarMakes().isEmpty());
    }

    @Test
    public void getCarMake() {
        CarMake carMake = carMakeDao.getCarMake(2);
        assertNotNull(carMake);
        assertEquals(2, carMake.getCarMakeId());
    }

    @Test
    public void addCarMake() {
        CarMake carMake = new CarMake(1, "Porsche");
        int carMakeId = carMakeDao.addCarMake(carMake);
        assertTrue(carMakeId > 0);
        CarMake newCarMake = carMakeDao.getCarMake(carMakeId);
        assertNotNull(newCarMake);
        assertEquals(carMakeId, newCarMake.getCarMakeId());
        assertEquals(carMake.getCarMake(), newCarMake.getCarMake());
        carMakeDao.deleteCarMake(carMakeId);
    }

    @Test
    public void updateCarMake() {
        int carMakeId = 2;
        CarMake oldCarMake = carMakeDao.getCarMake(carMakeId);
        oldCarMake.setCarMake("Ferrari");
        carMakeDao.updateCarMake(oldCarMake);
        CarMake newCarMake = carMakeDao.getCarMake(carMakeId);
        assertNotNull(newCarMake);
        assertEquals(oldCarMake.getCarMakeId(), newCarMake.getCarMakeId());
        assertEquals(oldCarMake.getCarMake(), newCarMake.getCarMake());
    }

    @Test
    public void deleteCarMake() {
        CarMake carMake = carMakeDao.getCarMake(1);
        carMake.setCarMake("Volkswagen");
        int carMakeId = carMakeDao.addCarMake(carMake);
        assertNotNull(carMakeDao.getCarMake(carMakeId));
        carMakeDao.deleteCarMake(carMakeId);
        assertNull(carMakeDao.getCarMake(carMakeId));
    }

    private static void createStatement(String createStatementSql) {
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createStatementSql);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }
}