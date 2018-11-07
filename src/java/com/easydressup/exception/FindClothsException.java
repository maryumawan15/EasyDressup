package com.easydressup.exception;

/**
 * Create user exception
 *
 * @author
 */
public class FindClothsException extends Exception {

    public FindClothsException() {
    }

    public FindClothsException(String message) {
        super(message);
    }

    public FindClothsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FindClothsException(Throwable cause) {
        super(cause);
    }

}
