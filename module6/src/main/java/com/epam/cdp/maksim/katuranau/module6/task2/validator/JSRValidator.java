package com.epam.cdp.maksim.katuranau.module6.task2.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

public class JSRValidator {

    private static final Logger LOGGER = LogManager.getLogger(JSRValidator.class);
    private Validator validator;

    public JSRValidator(Validator validator) {
        this.validator = validator;
    }

    public static JSRValidator createJSRValidator() {
        return new JSRValidator(Validation.buildDefaultValidatorFactory().getValidator());
    }

    public boolean isValid(Object o, Class clazz) {
        try {
            Set<ConstraintViolation<Object>> validate = validator.validate(o);
            if (validate.size() > 0) {
                validate.forEach(
                        employeeConstraintViolation -> {
                            LOGGER.error(employeeConstraintViolation.getMessageTemplate());
                        }
                );
                return false;
            }
        } catch (IllegalArgumentException error) {
            LOGGER.error("cannot be null");
            return false;
        }
        return true;
    }

    public boolean isValidValue(BigDecimal value) {
        return value.equals(value.abs());
    }

    public boolean isValidValue(double value) {
        return value >= 0;
    }

    @PostConstruct
    public void initialize() {
        LOGGER.info("Initialization phase!!!");
    }

    @PreDestroy
    public void destroy() {
        LOGGER.info("Destroy phase!!!");
    }
}
