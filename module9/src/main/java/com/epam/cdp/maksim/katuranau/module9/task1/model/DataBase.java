package com.epam.cdp.maksim.katuranau.module9.task1.model;

import java.util.Set;

public class DataBase {
    private String name;
    private Set<Table> tables;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Table> getTables() {
        return tables;
    }

    public void setTables(Set<Table> tables) {
        this.tables = tables;
    }
}
