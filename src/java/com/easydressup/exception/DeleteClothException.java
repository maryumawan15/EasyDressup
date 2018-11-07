package com.easydressup.exception;

/**
 * Create user exception
 *
 * @author
 */
public class DeleteClothException extends Exception {

    public DeleteClothException() {
    }

    public DeleteClothException(String message) {
        super(message);
    }

    public DeleteClothException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteClothException(Throwable cause) {
        super(cause);
    }
}