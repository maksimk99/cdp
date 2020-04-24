package com.epam.cdp.maksim.katuranau.module8.task3.dao;

import com.epam.cdp.maksim.katuranau.module8.task3.model.User;

import java.util.List;

public interface UserDao {

    List<User> read();

    void createUsers(List<User> users);

    Integer create(User user);

    List<User> readByFriendsAndLikesAndYearDuringMonth(int amountOfFriends, int amountOfLike, int year, int month);
}
