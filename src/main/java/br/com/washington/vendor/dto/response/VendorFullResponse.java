package br.com.washington.vendor.dto.response;

import java.util.UUID;

public record VendorFullResponse(
        UUID id,
        String name,
        String address,
        String city,
        String state,
        String zip,
        String country
) {}
