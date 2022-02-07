package com.springboot.mehedizaman.restcountries.exceptions;

public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomException() {
        super();
    }
    public CustomException(String msg) {
        super(msg);
    }
}
