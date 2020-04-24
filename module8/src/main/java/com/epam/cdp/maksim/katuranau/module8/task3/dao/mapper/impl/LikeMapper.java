package com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.impl;

import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Like;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LikeMapper implements RowMapper<Like> {
    private static final String POST_ID = "post_id";
    private static final String USER_ID = "user_id";
    private static final String DATE = "date";

    @Override
    public Like mapRow(ResultSet rs) throws SQLException {
        Like like = new Like();
        like.setPostId(rs.getInt(POST_ID));
        like.setUserId(rs.getInt(USER_ID));
        Timestamp date = rs.getTimestamp(DATE);
        if (date != null) {
            like.setTimestamp(date.toLocalDateTime());
        }
        return like;
    }
}
