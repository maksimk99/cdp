package com.epam.cdp.maksim.katuranau.module8.task1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdp_module8";
    private static final String DB_USER_NAME = "root";
    private static final String DB_PASSWORD = "16111999m";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_AGE = "user_age";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD)) {
            selectAllUsers(connection);
            selectUserNames(connection);
            selectUserById(connection, 3);
            printAllTables(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectAllUsers(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        String selectSql = "SELECT * FROM user";
        ResultSet resultSet = stmt.executeQuery(selectSql);
        System.out.println("List users: ");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(USER_ID) + " " + resultSet.getString(USER_NAME)
                    + " " + resultSet.getString(USER_AGE));
        }
    }

    private static void selectUserNames(Connection connection) throws SQLException {
        Statement stmt = connection.createStatement();
        String selectSql = "SELECT user_name FROM user";
        ResultSet resultSet = stmt.executeQuery(selectSql);
        System.out.print("\nList user names: ");
        while (resultSet.next()) {
            System.out.print(resultSet.getString(USER_NAME) + ", ");
        }
    }

    private static void selectUserById(Connection connection, int userId) throws SQLException {
        String selectSql = "SELECT * FROM user WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        System.out.println("\n\nUser with id = " + userId + resultSet.getInt(USER_ID)
                + " " + resultSet.getString(USER_NAME)
                + " " + resultSet.getString(USER_AGE));
    }

    private static void printAllTables(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.print("\nAll tables in cdp_module8 schema: ");
        try (ResultSet resultSet = metaData.getTables("cdp_module8", null, "%", new String[]{"TABLE"})) {
            while (resultSet.next()) {
                System.out.print(resultSet.getString("TABLE_NAME") + ", ");
            }
        }
    }
}
