package br.com.washington.vendor.util;

import br.com.washington.vendor.dto.request.ProductCreateRequest;
import br.com.washington.vendor.dto.response.ProductFullResponse;
import br.com.washington.vendor.entities.Product;
import br.com.washington.vendor.entities.Vendor;

public class ProductParse {

    public static ProductFullResponse toDTO(Product product){
        return new ProductFullResponse(
                VendorParse.toShortDTO(product.getVendor()),
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public static Product createByDTO(ProductCreateRequest request, Vendor vendor){
        return Product.builder()
                .vendor(vendor)
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();
    }
}
