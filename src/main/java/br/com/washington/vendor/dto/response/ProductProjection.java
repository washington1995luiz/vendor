package br.com.washington.vendor.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductProjection {
    UUID getId();
    String getName();
    String getDescription();
    BigDecimal getPrice();
}
