package com.foo.controller;

public class SomeServiceException extends RuntimeException {
    public SomeServiceException() {
        super("SomeServiceException");
    }
}
