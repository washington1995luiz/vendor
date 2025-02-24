package br.com.washington.vendor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VendorUpdateRequest(
        @NotNull @NotBlank String id,
        @Size(max = 100) String name,
        @Size(max = 255) String address,
        @Size(max = 100) String city,
        @Size(max = 50) String state,
        @Size(max = 20) String zip,
        @Size(max = 50) String country
) {
}
