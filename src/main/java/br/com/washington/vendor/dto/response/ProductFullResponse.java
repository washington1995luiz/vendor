package br.com.washington.vendor.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductFullResponse(
       VendorShortResponse vendor,
       UUID id,
       String name,
       String description,
       BigDecimal price
) {
}
