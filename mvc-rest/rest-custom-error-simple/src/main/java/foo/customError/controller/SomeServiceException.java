package foo.customError.controller;

public class SomeServiceException extends RuntimeException {
    public SomeServiceException() {
        super("SomeServiceException");
    }
}
