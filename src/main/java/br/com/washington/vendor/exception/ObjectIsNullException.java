package br.com.washington.vendor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ObjectIsNullException extends ResponseStatusException {
    public ObjectIsNullException() {
        super(HttpStatus.BAD_REQUEST, ErrorMessagesEnum.OBJECT_IS_NULL.getMessage());
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
