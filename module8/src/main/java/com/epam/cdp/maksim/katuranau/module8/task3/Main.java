package com.epam.cdp.maksim.katuranau.module8.task3;

import com.epam.cdp.maksim.katuranau.module8.task3.dao.DataBaseDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.FriendshipDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.LikeDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.PostDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.UserDao;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.conector.MySqlConnector;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.impl.DataBaseDaoImpl;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.impl.FriendshipDaoImpl;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.impl.LikeDaoImpl;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.impl.PostDaoImpl;
import com.epam.cdp.maksim.katuranau.module8.task3.dao.impl.UserDaoImpl;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Friendship;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Like;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Post;
import com.epam.cdp.maksim.katuranau.module8.task3.model.User;
import com.epam.cdp.maksim.katuranau.module8.task3.util.PropertyReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        MySqlConnector mySqlConnector = new MySqlConnector();
        Properties properties = PropertyReader.getProperties("task3/sql-query.properties");
        DataBaseDao dbDao = new DataBaseDaoImpl(mySqlConnector, properties);
        dbDao.createTables();
        dbDao.populate(30);

        UserDao userDao = new UserDaoImpl(mySqlConnector, properties);
        PostDao postDao = new PostDaoImpl(mySqlConnector, properties);
        LikeDao likeDao = new LikeDaoImpl(mySqlConnector, properties);
        FriendshipDao friendshipDao = new FriendshipDaoImpl(mySqlConnector, properties);

        System.out.println(userDao.read());
        System.out.println(postDao.read());
        System.out.println(likeDao.read());
        System.out.println(friendshipDao.read());

        Integer userId1 = userDao.create(new User("Maksim", "Hihol", LocalDate.now()));
        Integer userId2 = userDao.create(new User("Igor", "Sych", LocalDate.now()));
        Integer postId1 = postDao.create(new Post(userId1, "Some text for first user", LocalDateTime.now()));
        Integer postId2 = postDao.create(new Post(userId2, "Some text for first user", LocalDateTime.now()));
        likeDao.create(new Like(postId1, userId1, LocalDateTime.now()));
        likeDao.create(new Like(postId2, userId2, LocalDateTime.now()));
        friendshipDao.create(new Friendship(userId1, userId2, LocalDateTime.now()));
        friendshipDao.create(new Friendship(userId2, userId1, LocalDateTime.now()));

        userDao.readByFriendsAndLikesAndYearDuringMonth(1, 1, 2025, 3);
    }
}
