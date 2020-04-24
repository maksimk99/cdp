package com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.impl;

import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PostMapper implements RowMapper<Post> {
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String TEXT = "text";
    private static final String PUBLICATION_DATE = "publication_date";

    @Override
    public Post mapRow(ResultSet rs) throws SQLException {

        Post post = new Post();
        post.setId(rs.getInt(ID));
        post.setUserId(rs.getInt(USER_ID));
        post.setText(rs.getString(TEXT));
        Timestamp date = rs.getTimestamp(PUBLICATION_DATE);
        if (date != null) {
            post.setTimestamp(date.toLocalDateTime());
        }
        return post;
    }
}
