package com.epam.cdp.maksim.katuranau.module9.task1.exception;

import java.sql.SQLException;

public class DataBaseNotConnectedException extends SQLException {

    public DataBaseNotConnectedException(String message) {
        super(message);
    }
}
