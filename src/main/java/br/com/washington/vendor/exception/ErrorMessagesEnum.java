package br.com.washington.vendor.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessagesEnum {

    VENDOR_NOT_FOUND("Vendor not found."),
    PRODUCT_NOT_FOUND("Product not found for this ID: "),
    INVALID_UUID("Invalid UUID: "),
    OBJECT_IS_NULL("Object is null.");

    private final String message;
}
