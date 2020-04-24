package com.epam.cdp.maksim.katuranau.module8.task3.dao;

import com.epam.cdp.maksim.katuranau.module8.task3.model.Like;

import java.util.List;

public interface LikeDao {

    List<Like> read();

    void createLikes(List<Like> likes);

    void create(Like like);
}
