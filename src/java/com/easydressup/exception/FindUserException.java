package com.easydressup.exception;

/**
 * Find user exception
 *
 * @author
 */
public class FindUserException extends Exception {

    public FindUserException() {
    }

    public FindUserException(String message) {
        super(message);
    }

    public FindUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindUserException(Throwable cause) {
        super(cause);
    }

}
