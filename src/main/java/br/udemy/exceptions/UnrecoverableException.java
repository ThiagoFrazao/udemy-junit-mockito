package br.udemy.exceptions;

public class UnrecoverableException extends RuntimeException {

    public UnrecoverableException(String message) {
        super(message);
    }
}
