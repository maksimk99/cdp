package com.epam.cdp.maksim.katuranau.module8.task2;

import com.epam.cdp.maksim.katuranau.module8.task2.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.sql.CarDaoImpl;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.sql.CarMakeDaoImpl;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.sql.CarModelDaoImpl;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.stored.procedure.CarDaoStoredProcedure;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.stored.procedure.CarMakeDaoStoredProcedure;
import com.epam.cdp.maksim.katuranau.module8.task2.dao.stored.procedure.CarModelDaoStoredProcedure;
import com.epam.cdp.maksim.katuranau.module8.task2.util.PerformanceCalculator;
import com.epam.cdp.maksim.katuranau.module8.task2.util.PropertyReader;
import com.epam.cdp.maksim.katuranau.module8.task2.util.StoredProcedure;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        MySqlConnector mySqlConnector = new MySqlConnector();
        Properties sqlQueryProperties = PropertyReader.getProperties("task2/sql-query.properties");
        Properties storedProcedureProperties = PropertyReader
                .getProperties("task2/stored-procedure.properties");
        StoredProcedure storedProcedure = new StoredProcedure(mySqlConnector, storedProcedureProperties);
        storedProcedure.createStoredProcedures();
        System.out.println("\nAll stored procedures in database:\n" + storedProcedure.getStoredProcedureList() + "\n");

        PerformanceCalculator performanceCalculator = new PerformanceCalculator(
                new CarDaoImpl(mySqlConnector, sqlQueryProperties),
                new CarMakeDaoImpl(mySqlConnector, sqlQueryProperties),
                new CarModelDaoImpl(mySqlConnector, sqlQueryProperties));
        PerformanceCalculator performanceCalculatorWithStoredProcedure = new PerformanceCalculator(
                new CarDaoStoredProcedure(mySqlConnector, storedProcedureProperties),
                new CarMakeDaoStoredProcedure(mySqlConnector, storedProcedureProperties),
                new CarModelDaoStoredProcedure(mySqlConnector, storedProcedureProperties));

        long resultTimeWithoutStoredProcedure = performanceCalculator.logTimings();
        long resultTimeWithStoredProcedure = performanceCalculatorWithStoredProcedure.logTimings();

        System.out.println("\n********************\nPerformance JDBC without stored procedure:");
        System.out.println("Result time = " + resultTimeWithoutStoredProcedure + " nanoseconds.");
        System.out.println("\nPerformance JDBC with stored procedure:");
        System.out.println("Result time = " + resultTimeWithStoredProcedure + " nanoseconds.\n");
        System.out.println("JDBC with stored procedure is faster than without stored procedure for "
                + (resultTimeWithoutStoredProcedure - resultTimeWithStoredProcedure) + " nanoseconds.");
    }
}
