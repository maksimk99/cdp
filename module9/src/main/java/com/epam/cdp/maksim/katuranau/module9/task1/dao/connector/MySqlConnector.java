package com.epam.cdp.maksim.katuranau.module9.task1.dao.connector;

import com.epam.cdp.maksim.katuranau.module9.task1.exception.DataBaseNotConnectedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class MySqlConnector {
    private static final Logger LOGGER = LogManager.getLogger(MySqlConnector.class);

    private Properties properties;

    @Autowired
    public MySqlConnector(@Qualifier(value = "customProperties") final Properties properties) {
        this.properties = properties;
    }

    public Connection getConnection() throws DataBaseNotConnectedException {
        try {
            return DriverManager.getConnection(properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DataBaseNotConnectedException(properties.getProperty("jdbc.not.connected.message"));
        }
    }
}
