package com.epam.cdp.maksim.katuranau.module9.task1.util.db;

import com.epam.cdp.maksim.katuranau.module9.task1.model.Column;
import com.epam.cdp.maksim.katuranau.module9.task1.model.Table;
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

    public String generateSchemaCreatingQuery(String name) {
        return String.format(CREATE_SCHEMA, name);
    }

    public String[] generateTablesCreatingQuery(String dataBaseName, Set<Table> tables) {
        return tables.stream()
                .map(table -> generateTableCreatingQuery(dataBaseName, table))
                .toArray(String[]::new);
    }

    public String generateTableCreatingQuery(String schema, Table table) {
        return String.format(CREATE_TABLE_POSTFIX, schema, table.getName()) + generateColumnQuery(table.getColumns())
                + CREATE_TABLE_SUFFIX;
    }

    private String generateColumnQuery(Set<Column> columns) {
        return columns.stream()
                .map(column -> String.format(CREATE_TABLE_ATTRIBUTE, column.getName(), column.getType()))
                .collect(Collectors.joining(","));
    }
}
