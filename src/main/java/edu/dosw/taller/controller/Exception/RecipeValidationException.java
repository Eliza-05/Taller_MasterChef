package edu.dosw.taller.controller.Exception;

public class RecipeValidationException extends RuntimeException {
    public RecipeValidationException(String message) {
        super(message);
    }
}