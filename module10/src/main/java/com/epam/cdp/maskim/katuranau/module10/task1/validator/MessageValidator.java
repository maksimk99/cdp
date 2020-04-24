package com.epam.cdp.maskim.katuranau.module10.task1.validator;

import com.epam.cdp.maskim.katuranau.module10.task1.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;

public class MessageValidator {

    private static final String INCORRECT_NAME = "Incorrect message";

    public void validateName(String name) throws ValidationException {
        if (!StringUtils.isNotBlank(name)) {
            throw new ValidationException(INCORRECT_NAME);
        }
    }
}
