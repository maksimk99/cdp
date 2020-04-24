package com.epam.cdp.maksim.katuranau.module8.task3.dao.impl;

import com.epam.cdp.maksim.katuranau.module8.task3.dao.FriendshipDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.impl.FriendshipMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Friendship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FriendshipDaoImpl implements FriendshipDao {

    private static final Logger LOGGER = LogManager.getLogger(FriendshipDaoImpl.class);

    private MySqlConnector mySqlConnector;
    private Properties properties;

    public FriendshipDaoImpl(MySqlConnector mySqlConnector, Properties properties) {
        this.mySqlConnector = mySqlConnector;
        this.properties = properties;
    }

    @Override
    public List<Friendship> read() {
        List<Friendship> friendships = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("get.friendships.sql"));
            RowMapper<Friendship> rowMapper = new FriendshipMapper();
            while (resultSet.next()) {
                friendships.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return friendships;
    }

    @Override
    public void createFriendShips(List<Friendship> friendships) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.friendship.sql"))) {
            for (Friendship friendship : friendships) {
                preparedStatement.setInt(1, friendship.getUserId1());
                preparedStatement.setInt(2, friendship.getUserId2());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(friendship.getTimestamp()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public boolean checkFriendsExisting(Connection connection, Friendship friendship) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties
                .getProperty("check.or.friendship.exist.sql"))) {
            preparedStatement.setInt(1, friendship.getUserId1());
            preparedStatement.setInt(2, friendship.getUserId2());
            preparedStatement.setInt(3, friendship.getUserId2());
            preparedStatement.setInt(4, friendship.getUserId1());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) {
                LOGGER.error("Friendship is already exists");
                return true;
            }
            return false;
        }
    }

    @Override
    public void create(Friendship friendship) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.friendship.sql"),
                        Statement.RETURN_GENERATED_KEYS)) {
            if (!checkFriendsExisting(connection, friendship)) {
                preparedStatement.setInt(1, friendship.getUserId1());
                preparedStatement.setInt(2, friendship.getUserId2());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(friendship.getTimestamp()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
