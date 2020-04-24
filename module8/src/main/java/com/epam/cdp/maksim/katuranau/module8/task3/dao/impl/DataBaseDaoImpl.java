package com.epam.cdp.maksim.katuranau.module8.task3.dao.impl;

import com.epam.cdp.maksim.katuranau.module8.task3.dao.DataBaseDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.FriendshipDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.LikeDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.PostDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.UserDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task3.util.DataGeneratorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class DataBaseDaoImpl implements DataBaseDao {

    private static final Logger LOGGER = LogManager.getLogger(DataBaseDaoImpl.class);

    private static final String DATABASE_SCHEMA_FILE_NAME = "task3/schema.sql";

    private MySqlConnector mySqlConnector;
    private Properties properties;

    public DataBaseDaoImpl(MySqlConnector mySqlConnector, Properties properties) {
        this.mySqlConnector = mySqlConnector;
        this.properties = properties;
    }

    @Override
    public void createTables() {
        try (Connection connection = mySqlConnector.getConnection();
             Statement statement = connection.createStatement()) {
            Scanner scanner = new Scanner(Files.newInputStream(Paths.get(getClass().getClassLoader()
                    .getResource(DATABASE_SCHEMA_FILE_NAME).getPath()))).useDelimiter(";");
            while (scanner.hasNext()) {
                statement.execute(scanner.next());
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void populate(int amountOfUsers) {
        DataGeneratorUtil dataGenerator = new DataGeneratorUtil();
        UserDao userDao = new UserDaoImpl(mySqlConnector, properties);
        PostDao postDao = new PostDaoImpl(mySqlConnector, properties);
        LikeDao likeDao = new LikeDaoImpl(mySqlConnector, properties);
        FriendshipDao friendshipDao = new FriendshipDaoImpl(mySqlConnector, properties);

        userDao.createUsers(dataGenerator.generateUsers(amountOfUsers));
        friendshipDao.createFriendShips(dataGenerator.generateFriendships(amountOfUsers * 4,
                amountOfUsers));
        postDao.createPosts(dataGenerator.generatePosts(amountOfUsers * 300, amountOfUsers));
        likeDao.createLikes(dataGenerator.generateLikes(amountOfUsers * 300, amountOfUsers));
    }
}
