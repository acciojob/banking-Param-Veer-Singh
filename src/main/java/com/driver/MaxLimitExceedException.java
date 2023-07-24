package com.driver;

public class MaxLimitExceedException extends  Exception{
    public MaxLimitExceedException(String message) {
        super(message);
    }
}
