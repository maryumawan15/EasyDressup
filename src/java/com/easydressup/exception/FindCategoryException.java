package com.easydressup.exception;

/**
 * Create user exception
 *
 * @author
 */
public class FindCategoryException extends Exception {

    public FindCategoryException() {
    }

    public FindCategoryException(String message) {
        super(message);
    }

    public FindCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindCategoryException(Throwable cause) {
        super(cause);
    }

}
