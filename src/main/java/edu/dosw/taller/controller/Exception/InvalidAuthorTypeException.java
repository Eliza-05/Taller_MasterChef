package edu.dosw.taller.controller.Exception;

public class InvalidAuthorTypeException extends RuntimeException {
    public InvalidAuthorTypeException(String message) {
        super(message);
    }
}