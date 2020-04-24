package com.epam.cdp.maksim.katuranau.module8.task2.util;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.conector.MySqlConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class StoredProcedure {

    private static final Logger LOGGER = LogManager.getLogger(StoredProcedure.class);

    private MySqlConnector mySqlConnector;
    private Properties properties;

    public StoredProcedure(MySqlConnector mySqlConnector, Properties properties) {
        this.mySqlConnector = mySqlConnector;
        this.properties = properties;
    }

    public List<String> getStoredProcedureList() {
        List<String> storedProcedureList = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("stored.procedure.list.get.sql"));
            while (resultSet.next()) {
                storedProcedureList.add(resultSet.getString(1));
            }
            LOGGER.info("Return list of stored procedures");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return storedProcedureList;
    }

    public void dropAllStoredProcedure() {
        List<String> storedProcedureList = getStoredProcedureList();
        if (!storedProcedureList.isEmpty()) {
            try (Connection connection = mySqlConnector.getConnection();
                 Statement statement = connection.createStatement()) {
                for (String storedProcedure : storedProcedureList) {
                    statement.execute("DROP PROCEDURE IF EXISTS " + storedProcedure);
                }
                LOGGER.info("Stored procedures were dropped");
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public void createStoredProcedures() {
        dropAllStoredProcedure();
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            Scanner scanner = new Scanner(Files.newInputStream(Paths.get(getClass().getClassLoader()
                    .getResource(properties.getProperty("stored.procedure.file.name")).getPath())))
                    .useDelimiter(properties.getProperty("stored.procedure.file.delimiter"));
            while (scanner.hasNext()) {
                String query = scanner.next();
                statement.execute(query + properties.getProperty("stored.procedure.file.delimiter"));
            }
            LOGGER.info("Stored procedures were created");
        } catch (SQLException | IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
