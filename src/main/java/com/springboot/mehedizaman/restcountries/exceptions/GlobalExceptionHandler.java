package com.springboot.mehedizaman.restcountries.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handle specific Exception
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException exc, WebRequest request) {
        System.out.println("FileNotFoundException");
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exc.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException exc, WebRequest request) {
        System.out.println("IOException");
        ErrorDetails errorDetails = new ErrorDetails(new Date(),
                exc.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    // handle custom Exception
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException exc, WebRequest request) {
        System.out.println("CustomException");
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exc.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        // TODO Auto-generated method stub
        return new ResponseEntity<Object>("Parth variable is not provide", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<RuntimeException> handleAllExceptions(RuntimeException ex) {
        System.out.println("RuntimeException");
        return new ResponseEntity<RuntimeException>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // handle global Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobleException(Exception exc, WebRequest request) {
        System.out.println("GlobalException");
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Requested data is not available",
                request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
