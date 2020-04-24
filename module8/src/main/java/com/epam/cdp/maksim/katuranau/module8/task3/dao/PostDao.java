package com.epam.cdp.maksim.katuranau.module8.task3.dao;

import com.epam.cdp.maksim.katuranau.module8.task3.model.Post;

import java.util.List;

public interface PostDao {

    List<Post> read();

    void createPosts(List<Post> posts);

    Integer create(Post post);
}
