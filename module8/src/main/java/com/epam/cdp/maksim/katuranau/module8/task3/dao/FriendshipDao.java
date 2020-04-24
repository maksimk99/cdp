package com.epam.cdp.maksim.katuranau.module8.task3.dao;

import com.epam.cdp.maksim.katuranau.module8.task3.model.Friendship;

import java.util.List;

public interface FriendshipDao {

    List<Friendship> read();

    void createFriendShips(List<Friendship> friendships);

    void create(Friendship friendship);
}
