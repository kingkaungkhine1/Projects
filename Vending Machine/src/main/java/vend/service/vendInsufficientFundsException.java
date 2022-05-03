package vend.service;

public class vendInsufficientFundsException extends Exception{
    public vendInsufficientFundsException(String message) {
        super(message);
    }

    public vendInsufficientFundsException(String message, Throwable cause)
    {
        super(message, cause);
    }
}