package com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.impl;

import com.epam.cdp.maksim.katuranau.module8.task3.dao.mapper.RowMapper;
import com.epam.cdp.maksim.katuranau.module8.task3.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserMapper implements RowMapper<User> {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String BIRTH_DATE = "birth_date";

    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(ID));
        user.setName(rs.getString(NAME));
        user.setSurname(rs.getString(SURNAME));
        Timestamp date = rs.getTimestamp(BIRTH_DATE);
        if (date != null) {
            user.setBirthDate(date.toLocalDateTime().toLocalDate());
        }
        return user;
    }
}
