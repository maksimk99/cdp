package com.epam.cdp.maksim.katuranau.module9.task1.util.db;

import com.epam.cdp.maksim.katuranau.module9.task1.model.Column;
import com.epam.cdp.maksim.katuranau.module9.task1.model.DataBase;
import com.epam.cdp.maksim.katuranau.module9.task1.model.Table;
import com.epam.cdp.maksim.katuranau.module9.task1.util.generation.DataGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DataBaseGenerator {

    private DataGeneratorUtil dataGeneratorUtil;
    private int amountOfTables;
    private int amountOfColumns;


    @Autowired
    public DataBaseGenerator(DataGeneratorUtil dataGeneratorUtil,
                             @Qualifier(value = "customProperties") Properties properties) {
        this.dataGeneratorUtil = dataGeneratorUtil;
        this.amountOfTables = Integer.parseInt(properties.getProperty("db.amountOfTables"));
        this.amountOfColumns = Integer.parseInt(properties.getProperty("db.amountOfColumns"));
    }

    public DataBase generateDataBase() {
        DataBase dataBase = new DataBase();
        dataBase.setName(dataGeneratorUtil.generateDataBaseName());
        dataBase.setTables(generateTables(amountOfTables));
        return dataBase;
    }

    private Set<Table> generateTables(int amountOfTables) {
        return IntStream.range(0, amountOfTables)
                .mapToObj(value -> generateTable())
                .collect(Collectors.toSet());
    }

    private Table generateTable() {
        Table table = new Table();
        table.setName(dataGeneratorUtil.generateTableName());
        table.setColumns(generateColumns(amountOfColumns));
        return table;
    }

    private Set<Column> generateColumns(int amountOfColumns) {
        return IntStream.range(0, amountOfColumns)
                .mapToObj(value -> generateColumn())
                .collect(Collectors.toSet());
    }

    private Column generateColumn() {
        Column column = new Column();
        column.setName(dataGeneratorUtil.generateColumnName());
        column.setType(dataGeneratorUtil.generateSQLType());
        return column;
    }
}
