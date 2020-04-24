package com.epam.cdp.maksim.katuranau.module11.controller.handler;

import com.epam.cdp.maksim.katuranau.module11.exception.InternalServerException;
import com.epam.cdp.maksim.katuranau.module11.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;

/**
 * The type Error handler.
 */
@ControllerAdvice
public class ErrorHandler {

    /**
     * Handle validation exception string.
     *
     * @param exception the exception
     * @return the string
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleValidationException(final ValidationException exception) {
        return createModelAndView(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    /**
     * Handle internal server exception string.
     *
     * @param exception the exception
     * @return the string
     */
    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleInternalServerException(final InternalServerException exception) {
        return createModelAndView(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    /**
     * Handle internal server exception string.
     *
     * @param exception the exception
     * @return the string
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleConstraintViolationException(final ConstraintViolationException exception) {
        return createModelAndView(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    /**
     * Handle internal server error string.
     *
     * @param exception the exception
     * @return the string
     */
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleInternalServerException(final HttpServerErrorException.InternalServerError exception) {
        return createModelAndView(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private ModelAndView createModelAndView(final HttpStatus errorStatus, final String errorMsg) {
        ModelAndView model = new ModelAndView();
        model.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        model.addObject("errorMessage", errorMsg);
        model.addObject("errorCode", errorStatus);
        model.setViewName("errorPage");
        return model;
    }
}
