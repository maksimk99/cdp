package com.epam.cdp.maksim.katuranau.module8.task3.dao.impl;

import com.epam.cdp.maksim.katuranau.module8.task3.dao.UserDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.impl.UserMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    private MySqlConnector mySqlConnector;
    private Properties properties;

    public UserDaoImpl(MySqlConnector mySqlConnector, Properties properties) {
        this.mySqlConnector = mySqlConnector;
        this.properties = properties;
    }

    @Override
    public void createUsers(List<User> users) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.user.sql"))) {
            for (User user : users) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getSurname());
                preparedStatement.setDate(3, Date.valueOf(user.getBirthDate()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<User> read() {
        List<User> users = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("get.users.sql"));
            RowMapper<User> rowMapper = new UserMapper();
            while (resultSet.next()) {
                users.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public Integer create(User user) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.user.sql"), Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setDate(3, Date.valueOf(user.getBirthDate()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return -1;
        }
    }

    @Override
    public List<User> readByFriendsAndLikesAndYearDuringMonth(int amountOfFriends, int amountOfLike, int year,
                                                              int month) {
        List<User> users = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("get.users.with.filters.sql"))) {
            preparedStatement.setInt(1, amountOfFriends);
            preparedStatement.setDate(2, new Date(year - 1900, 0, 1));
            preparedStatement.setDate(3, new Date(year - 1900, 11, 30));
            preparedStatement.setInt(4, amountOfLike);
            ResultSet resultSet = preparedStatement.executeQuery();
            RowMapper<User> rowMapper = new UserMapper();
            while (resultSet.next()) {
                users.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(users);
        return users;
    }
}
