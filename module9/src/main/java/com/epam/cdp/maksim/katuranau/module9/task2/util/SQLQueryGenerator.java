package com.epam.cdp.maksim.katuranau.module9.task2.util;

import com.epam.cdp.maksim.katuranau.module9.task2.model.Column;
import com.epam.cdp.maksim.katuranau.module9.task2.model.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SQLQueryGenerator {

    @Value("${create.schema}")
    private String CREATE_SCHEMA;
    @Value("${create.table.postfix}")
    private String CREATE_TABLE_POSTFIX;
    @Value("${create.table.attribute}")
    private String CREATE_TABLE_ATTRIBUTE;
    @Value("${create.table.suffix}")
    private String CREATE_TABLE_SUFFIX;
    @Value("${select.rows.sql}")
    private String SELECT_ROWS_SQL;
    @Value("${insert.rows.sql}")
    private String INSERT_ROW_SQL;

    public String generateSchemaCreatingQuery(String name) {
        return String.format(CREATE_SCHEMA, name);
    }

    public String[] generateTablesCreatingQuery(String dataBaseName, Set<Table> tables) {
        return tables.stream()
                .map(table -> generateTableCreatingQuery(dataBaseName, table))
                .toArray(String[]::new);
    }

    private String generateTableCreatingQuery(String schema, Table table) {
        return String.format(
                "%s%s%s",
                String.format(CREATE_TABLE_POSTFIX, schema, table.getName()),
                generateColumnQuery(table.getColumns()),
                CREATE_TABLE_SUFFIX
        );
    }

    private String generateColumnQuery(Set<Column> columns) {
        return columns.stream()
                .map(column -> String.format(CREATE_TABLE_ATTRIBUTE, column.getName(), column.getType()))
                .collect(Collectors.joining(","));
    }

    public String getSelectRowsSql(String schema, String tableName) {
        return String.format(SELECT_ROWS_SQL, schema, tableName);
    }

    public String getInsertRowSql(String schema, String tableName, Set<String> keySet) {
        StringBuilder keys = new StringBuilder();
        StringBuilder values = new StringBuilder();
        keySet.forEach((key) -> {
            keys.append(key).append(", ");
            values.append("?, ");
        });
        keys.deleteCharAt(keys.lastIndexOf(","));
        values.deleteCharAt(values.lastIndexOf(","));
        return String.format(INSERT_ROW_SQL, schema, tableName, keys.toString().trim(), values.toString().trim());
    }
}
