package com.epam.cdp.maksim.katuranau.module9.task2.dao.connector;

import com.epam.cdp.maksim.katuranau.module9.task1.exception.DataBaseNotConnectedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class MySqlConnector {

    private static final Logger LOGGER = LogManager.getLogger(MySqlConnector.class);

    @Value("${jdbc.url}")
    private String JDBC_URL;
    @Value("${jdbc.username}")
    private String JDBC_USERNAME;
    @Value("${jdbc.password}")
    private String JDBC_PASSWORD;
    @Value("${jdbc.not.connected.message}")
    private String DATABASE_ERROR_MESSAGE;

    public Connection getConnection() throws DataBaseNotConnectedException {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DataBaseNotConnectedException(DATABASE_ERROR_MESSAGE);
        }
    }
}
