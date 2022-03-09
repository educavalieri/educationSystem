package com.cavalieri.educationSystem.services.exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String msg){

        super(msg);
    }

}
