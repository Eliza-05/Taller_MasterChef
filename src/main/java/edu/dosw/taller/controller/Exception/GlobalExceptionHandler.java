package edu.dosw.taller.controller.Exception;

import edu.dosw.taller.controller.dtos.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleRecipeNotFoundException(
            RecipeNotFoundException ex, HttpServletRequest request) {
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            HttpStatus.NOT_FOUND.value(),
            "Recipe Not Found",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidAuthorTypeException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidAuthorTypeException(
            InvalidAuthorTypeException ex, HttpServletRequest request) {
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Author Type",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSeasonException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidSeasonException(
            InvalidSeasonException ex, HttpServletRequest request) {
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Season",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RecipeValidationException.class)
    public ResponseEntity<ErrorResponseDto> handleRecipeValidationException(
            RecipeValidationException ex, HttpServletRequest request) {
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            HttpStatus.BAD_REQUEST.value(),
            "Recipe Validation Error",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> handleResponseStatusException(
            ResponseStatusException ex, HttpServletRequest request) {
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            ex.getStatusCode().value(),
            ex.getStatusCode().toString(),
            ex.getReason(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

   
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {
        
        String message = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .reduce((msg1, msg2) -> msg1 + ", " + msg2)
            .orElse("Error de validación en los datos enviados");
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            HttpStatus.BAD_REQUEST.value(),
            "Validation Error",
            message,
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpServletRequest request) {
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            HttpStatus.BAD_REQUEST.value(),
            "Invalid Argument",
            ex.getMessage(),
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

 
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDto> handleNullPointerException(
            NullPointerException ex, HttpServletRequest request) {
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Null Pointer Error",
            "Ha ocurrido un error interno debido a un valor nulo no esperado",
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
            Exception ex, HttpServletRequest request) {
        
        ErrorResponseDto errorResponse = new ErrorResponseDto(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Internal Server Error",
            "Ha ocurrido un error interno del servidor. Por favor, contacte al administrador.",
            request.getRequestURI()
        );
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
