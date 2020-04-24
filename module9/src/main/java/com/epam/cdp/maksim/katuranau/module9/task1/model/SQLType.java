package com.epam.cdp.maksim.katuranau.module9.task1.model;

public enum SQLType {

    INT("INT"),
    TINYINT("TINYINT"),
    DATE("DATE"),
    DATETIME("DATETIME"),
    SMALLINT("SMALLINT"),
    MEDIUMINT("MEDIUMINT"),
    BIGINT("BIGINT");

    private String type;

    SQLType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
