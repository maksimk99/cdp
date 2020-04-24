package com.epam.cdp.maksim.katuranau.module11.exception.security;

/**
 * The type User already exist exception.
 */
public class UserAlreadyExistException extends RuntimeException {

    /**
     * Instantiates a new User already exist exception.
     *
     * @param s the s
     */
    public UserAlreadyExistException(String s) {
        super(s);
    }
}
