package com.epam.cdp.maksim.katuranau.module9.task2.model;

import java.util.Set;

public class Table {
    private String name;
    private Set<Column> columns;

    public Table(String name, Set<Column> columns) {
        this.name = name;
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Column> getColumns() {
        return columns;
    }

    public void setColumns(Set<Column> columns) {
        this.columns = columns;
    }
}
