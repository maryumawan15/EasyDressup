package com.easydressup.exception;

/**
 * Create user exception
 *
 * @author
 */
public class CreateClothException extends Exception {

    public CreateClothException() {
    }

    public CreateClothException(String message) {
        super(message);
    }

    public CreateClothException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateClothException(Throwable cause) {
        super(cause);
    }

}
