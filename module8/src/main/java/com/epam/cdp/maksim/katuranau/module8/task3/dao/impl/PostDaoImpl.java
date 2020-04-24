package com.epam.cdp.maksim.katuranau.module8.task3.dao.impl;

import com.epam.cdp.maksim.katuranau.module8.task3.dao.PostDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.impl.PostMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Post;
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

public class PostDaoImpl implements PostDao {

    private static final Logger LOGGER = LogManager.getLogger(PostDaoImpl.class);

    private MySqlConnector mySqlConnector;
    private Properties properties;

    public PostDaoImpl(MySqlConnector mySqlConnector, Properties properties) {
        this.mySqlConnector = mySqlConnector;
        this.properties = properties;
    }

    @Override
    public List<Post> read() {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("get.posts.sql"));
            RowMapper<Post> rowMapper = new PostMapper();
            while (resultSet.next()) {
                posts.add(rowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return posts;
    }

    @Override
    public void createPosts(List<Post> posts) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.post.sql"))) {
            for (Post post : posts) {
                preparedStatement.setInt(1, post.getUserId());
                preparedStatement.setString(2, post.getText());
                preparedStatement.setTimestamp(3, Timestamp.valueOf(post.getTimestamp()));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public Integer create(Post post) {
        try (Connection connection = mySqlConnector.getConnection(); PreparedStatement preparedStatement =
                connection.prepareStatement(properties.getProperty("add.post.sql"), Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, post.getUserId());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(post.getTimestamp()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return -1;
        }
    }
}
