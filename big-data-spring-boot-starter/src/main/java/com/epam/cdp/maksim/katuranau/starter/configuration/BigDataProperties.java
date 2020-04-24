package com.epam.cdp.maksim.katuranau.starter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bigdata.datasource")
public class BigDataProperties {

    private String schemaName;
    private Integer rowsAmount = 60;
    private String rowsAmountSelect = "SELECT SUM(TABLE_ROWS) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = :schema";

    public String getSchemaName() {
        String schemaNameSystemProperty = System.getProperty("bigdata.datasource.schemaname");
        return schemaNameSystemProperty == null ? schemaName : schemaNameSystemProperty;
    }

    public void setSchemaName(final String schemaName) {
        this.schemaName = schemaName;
    }

    public Integer getRowsAmount() {
        String rowsAmountSystemProperty = System.getProperty("bigdata.datasource.rowsamount");
        return rowsAmountSystemProperty == null ? rowsAmount : Integer.parseInt(rowsAmountSystemProperty);
    }

    public void setRowsAmount(final Integer rowsAmount) {
        this.rowsAmount = rowsAmount;
    }

    public String getRowsAmountSelect() {
        String rowsAmountSelectSystemProperty = System.getProperty("bigdata.datasource.rowsamount");
        return rowsAmountSelectSystemProperty == null ? rowsAmountSelect : rowsAmountSelectSystemProperty;
    }

    public void setRowsAmountSelect(final String rowsAmountSelect) {
        this.rowsAmountSelect = rowsAmountSelect;
    }
}
