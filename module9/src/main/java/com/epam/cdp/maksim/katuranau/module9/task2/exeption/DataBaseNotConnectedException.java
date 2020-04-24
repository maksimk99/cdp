package com.epam.cdp.maksim.katuranau.module9.task2.exeption;

import java.sql.SQLException;

public class DataBaseNotConnectedException extends SQLException {

    public DataBaseNotConnectedException(String message) {
        super(message);
    }
}
