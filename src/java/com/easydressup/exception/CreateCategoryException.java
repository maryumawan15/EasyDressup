package com.easydressup.exception;

/**
 * Create user exception
 *
 * @author
 */
public class CreateCategoryException extends Exception {

    public CreateCategoryException() {
    }

    public CreateCategoryException(String message) {
        super(message);
    }

    public CreateCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateCategoryException(Throwable cause) {
        super(cause);
    }

}
