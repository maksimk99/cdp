package com.epam.cdp.maksim.katuranau.module8.task3.dao.impl;

import com.epam.cdp.maksim.katuranau.module8.task3.dao.LikeDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.impl.LikeMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Like;
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

public class LikeDaoImpl implements LikeDao {

    private static final Logger LOGGER = LogManager.getLogger(LikeDaoImpl.class);

    private MySqlConnector mySqlConnector;
    private Properties properties;

    public LikeDaoImpl(MySqlConnector mySqlConnector, Properties properties) {
        this.mySqlConnector = mySqlConnector;
        this.properties = properties;
    }


    @Override
    public List<Like> read() {
        List<Like> likes = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("get.likes.sql"));
            RowMapper<Like> rowMapper = new LikeMapper();
            while (resultSet.next()) {
                likes.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return likes;
    }

    @Override
    public void createLikes(List<Like> likes) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.like.sql"))) {
            for (Like like : likes) {
                preparedStatement.setInt(1, like.getPostId());
                preparedStatement.setInt(2, like.getUserId());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(like.getTimestamp()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void create(Like like) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.like.sql"))) {
            preparedStatement.setInt(1, like.getPostId());
            preparedStatement.setInt(2, like.getUserId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(like.getTimestamp()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
