package br.com.washington.vendor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VendorNotFoundException extends ResponseStatusException {
    public VendorNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, ErrorMessagesEnum.VENDOR_NOT_FOUND.getMessage() + id);
    }
    @Override
    public Throwable fillInStackTrace() {
        // Return this instance without populating the stack trace
        return this;
    }
}
