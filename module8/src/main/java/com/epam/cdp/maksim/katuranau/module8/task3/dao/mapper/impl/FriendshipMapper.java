package com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.impl;

import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.model.Friendship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FriendshipMapper implements RowMapper<Friendship> {
    private static final String USER_ID_1 = "user_id1";
    private static final String USER_ID_2 = "user_id2";
    private static final String START_TIME = "start_time";

    @Override
    public Friendship mapRow(ResultSet rs) throws SQLException {
        Friendship friendship = new Friendship();
        friendship.setUserId1(rs.getInt(USER_ID_1));
        friendship.setUserId2(rs.getInt(USER_ID_2));
        Timestamp date = rs.getTimestamp(START_TIME);
        if (date != null) {
            friendship.setTimestamp(date.toLocalDateTime());
        }
        return friendship;
    }
}
