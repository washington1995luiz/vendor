package br.com.washington.vendor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidUUIDException extends ResponseStatusException {
    public InvalidUUIDException(String invalidId) {
        super(HttpStatus.BAD_REQUEST, ErrorMessagesEnum.INVALID_UUID.getMessage() + invalidId);
    }

    @Override
    public Throwable fillInStackTrace() {
        // Return this instance without populating the stack trace
        return this;
    }
}
