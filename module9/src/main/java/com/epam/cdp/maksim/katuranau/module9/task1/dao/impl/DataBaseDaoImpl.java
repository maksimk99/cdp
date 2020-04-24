package com.epam.cdp.maksim.katuranau.module9.task1.dao.impl;

import com.epam.cdp.maksim.katuranau.module9.task1.dao.DataBaseDao;
import com.epam.cdp.maksim.katuranau.module9.task1.dao.connector.MySqlConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DataBaseDaoImpl implements DataBaseDao {

    private static final Logger LOGGER = LogManager.getLogger(DataBaseDaoImpl.class);

    private MySqlConnector mySqlConnector;

    @Autowired
    public DataBaseDaoImpl(MySqlConnector mySqlConnector) {
        this.mySqlConnector = mySqlConnector;
    }

    @Override
    public void execute(String... queries) {
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            for (String query : queries) {
                statement.addBatch(query);
            }
            statement.executeBatch();
        } catch (SQLException error) {
            LOGGER.error(error.getMessage());
        }
    }
}
