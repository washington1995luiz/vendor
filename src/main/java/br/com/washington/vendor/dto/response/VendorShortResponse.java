package br.com.washington.vendor.dto.response;

import java.util.UUID;

public record VendorShortResponse(
        UUID id,
        String name
) {
}
