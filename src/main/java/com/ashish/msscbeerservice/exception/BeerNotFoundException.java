package com.ashish.msscbeerservice.exception;

public class BeerNotFoundException extends RuntimeException{
    public BeerNotFoundException(String message){
        super(message);
    }
}
