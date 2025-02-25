package br.com.washington.vendor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        @NotNull @NotBlank String id,
        @Size(max = 100) String name,
        String description,
        BigDecimal price
) {
}
