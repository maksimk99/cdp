package com.epam.cdp.maksim.katuranau.module8.task2.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    T mapRow(ResultSet rs) throws SQLException;
}

