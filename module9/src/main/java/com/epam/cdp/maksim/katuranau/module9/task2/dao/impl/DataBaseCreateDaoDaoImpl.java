package com.epam.cdp.maksim.katuranau.module9.task2.dao.impl;

import com.epam.cdp.maksim.katuranau.module9.task2.dao.DataBaseCreateDao;
import com.epam.cdp.maksim.katuranau.module9.task2.dao.connector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module9.task2.model.Column;
import com.epam.cdp.maksim.katuranau.module9.task2.model.DataBase;
import com.epam.cdp.maksim.katuranau.module9.task2.model.Table;
import com.epam.cdp.maksim.katuranau.module9.task2.util.SQLQueryGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class DataBaseCreateDaoDaoImpl implements DataBaseCreateDao {

    private static final Logger LOGGER = LogManager.getLogger(DataBaseCreateDaoDaoImpl.class);
    private static final String NEW_SCHEMA_NAME = "copied_database";
    private MySqlConnector mySqlConnector;
    private SQLQueryGenerator sqlQueryGenerator;

    @Autowired
    public DataBaseCreateDaoDaoImpl(MySqlConnector mySqlConnector, SQLQueryGenerator sqlQueryGenerator) {
        this.mySqlConnector = mySqlConnector;
        this.sqlQueryGenerator = sqlQueryGenerator;
    }

    @Override
    public DataBase createDatabase(String schemaName) {
        DataBase dataBase = getDataBase(schemaName);
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlQueryGenerator.generateSchemaCreatingQuery(dataBase.getName()));
            for (String query : sqlQueryGenerator.generateTablesCreatingQuery(dataBase.getName(),
                    dataBase.getTables())) {
                statement.addBatch(query);
            }
            statement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return dataBase;
    }

    private DataBase getDataBase(String schemaName) {
        try (Connection connection = mySqlConnector.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            Set<Table> tableSet = new LinkedHashSet<>();
            for (String tableName : getTablesSet(metaData, schemaName)) {
                tableSet.add(new Table(tableName, getColumnsSet(metaData, schemaName, tableName)));
            }
            return new DataBase(NEW_SCHEMA_NAME, tableSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    private Set<Column> getColumnsSet(DatabaseMetaData metaData, String schemaName, String tableName) {
        Set<Column> columnSet = new LinkedHashSet<>();
        try (ResultSet rs = metaData.getColumns(schemaName, null, tableName, null)) {
            while (rs.next()) {
                String columnType = rs.getString("TYPE_NAME");
                if (columnType.equals("VARCHAR")) {
                    columnType = String.format("%s(%s)", columnType, rs.getString("COLUMN_SIZE"));
                }
                columnSet.add(new Column(rs.getString("COLUMN_NAME"), columnType));
            }
            return columnSet;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return columnSet;
    }

    private Set<String> getTablesSet(DatabaseMetaData metaData, String schemaName) {
        Set<String> tables = new LinkedHashSet<>();
        try (ResultSet resultSet = metaData.getTables(schemaName, null, "%", new String[]{"TABLE"})) {
            while (resultSet.next()) {
                tables.add(resultSet.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return tables;
    }
}