package foo.customError.controller;

import foo.customError.controller.dto.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {SomeServiceException.class})
    protected ResponseEntity handleSomeServiceException(
            RuntimeException ex, WebRequest request) {

        log.warn(request.toString());
        return handleExceptionInternal(ex, ApiError.of("Entity Not Found", "Entity was not found, not commissioned"),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Default fall back
     */
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity fallBackHandleException(
            RuntimeException ex, WebRequest request) {

        log.warn(request.toString());
        return handleExceptionInternal(ex, ApiError.of("Internal Server Error", ex.getMessage() != null ?ex.getMessage().toString():"Unspecified internal Error"),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
