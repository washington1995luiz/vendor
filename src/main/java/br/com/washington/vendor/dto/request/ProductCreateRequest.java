package br.com.washington.vendor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductCreateRequest(
        @NotNull @NotBlank String vendor,
        @NotNull @NotBlank String name,
        @NotNull BigDecimal price,
        String description
        ) {
}
