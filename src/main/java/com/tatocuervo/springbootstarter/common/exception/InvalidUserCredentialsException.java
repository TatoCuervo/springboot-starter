package com.tatocuervo.springbootstarter.common.exception;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
        super("Incorrect username or password ");
    }
}
