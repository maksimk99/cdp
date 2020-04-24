package com.epam.cdp.maksim.katuranau.module8.task2.dao.conector;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.sql.CarDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnector {

    private static final Logger LOGGER = LogManager.getLogger(CarDaoImpl.class);
    private static final String PROPERTIES_FILE_NAME = "jdbc.properties";

    private Properties properties;

    public MySqlConnector() {
        this.properties = new Properties();
        try (FileInputStream fileInputStream =
                     new FileInputStream(getClass().getClassLoader().getResource(PROPERTIES_FILE_NAME).getFile())) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}
