package com.epam.cdp.maksim.katuranau.module9.task2.dao.impl;

import com.epam.cdp.maksim.katuranau.module9.task2.dao.DataBasePopulateDao;
import com.epam.cdp.maksim.katuranau.module9.task2.dao.connector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module9.task2.model.Column;
import com.epam.cdp.maksim.katuranau.module9.task2.model.DataBase;
import com.epam.cdp.maksim.katuranau.module9.task2.model.Table;
import com.epam.cdp.maksim.katuranau.module9.task2.util.SQLQueryGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Component
public class DataBasePopulateDaoDaoImpl implements DataBasePopulateDao {

    private static final Logger LOGGER = LogManager.getLogger(DataBasePopulateDaoDaoImpl.class);

    @Value("${populate.data.error.message}")
    private String POPULATE_DATA_ERROR;

    private MySqlConnector mySqlConnector;
    private SQLQueryGenerator sqlQueryGenerator;

    @Autowired
    public DataBasePopulateDaoDaoImpl(MySqlConnector mySqlConnector, SQLQueryGenerator sqlQueryGenerator) {
        this.mySqlConnector = mySqlConnector;
        this.sqlQueryGenerator = sqlQueryGenerator;
    }

    @Override
    public void populateData(DataBase dataBase, String oldSchemaName, String order) {
        if (Objects.nonNull(dataBase)) {
            for (Table table : dataBase.getTables()) {
                insertRows(dataBase.getName(), table.getName(),
                        selectRows(oldSchemaName, table.getName(), table.getColumns(), order));
            }
        } else {
            LOGGER.error(POPULATE_DATA_ERROR);
        }
    }

    private void insertRows(String schema, String table, Map<Set<String>, List<List<Object>>> mapKeyValues) {
        try (Connection connection = mySqlConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQueryGenerator.getInsertRowSql(
                     schema, table, mapKeyValues.keySet().iterator().next()))) {
            for (List<Object> values : mapKeyValues.values().iterator().next()) {
                for (int i = 0; i < values.size(); i++) {
                    preparedStatement.setObject(i + 1, values.get(i));
                }
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Map<Set<String>, List<List<Object>>> selectRows(String schema, String table, Set<Column> columnSet,
                                                            String order) {
        Map<Set<String>, List<List<Object>>> mapKeyValues = new HashMap<>();
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            Set<String> keySet = new LinkedHashSet<>();
            columnSet.forEach(column -> keySet.add(column.getName()));

            List<List<Object>> listRows = new LinkedList<>();
            ResultSet resultSet = statement.executeQuery(sqlQueryGenerator.getSelectRowsSql(schema, table));
            while (resultSet.next()) {
                List<Object> values = new LinkedList<>();
                for (String column : keySet) {
                    values.add(resultSet.getObject(column));
                }
                if (!values.isEmpty()) {
                    listRows.add(values);
                }
            }

            if (order.equals("reverse")) {
                Collections.reverse(listRows);
            }

            mapKeyValues.put(keySet, listRows);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return mapKeyValues;
    }

}
