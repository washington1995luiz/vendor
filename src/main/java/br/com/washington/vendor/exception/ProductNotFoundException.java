package br.com.washington.vendor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductNotFoundException extends ResponseStatusException {
    public ProductNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorMessagesEnum.VENDOR_NOT_FOUND.getMessage());
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
