package vend.service;

public class vendDataValidationException extends Exception {
    public vendDataValidationException(String message) {
        super(message);
    }

    public vendDataValidationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
