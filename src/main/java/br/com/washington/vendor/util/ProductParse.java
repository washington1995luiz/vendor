package br.com.washington.vendor.util;

import br.com.washington.vendor.dto.request.ProductCreateRequest;
import br.com.washington.vendor.dto.request.ProductUpdateRequest;
import br.com.washington.vendor.dto.response.ProductFullResponse;
import br.com.washington.vendor.dto.response.ProductShortResponse;
import br.com.washington.vendor.entities.Product;
import br.com.washington.vendor.entities.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

import java.util.Optional;

public class ProductParse {

    public static ProductFullResponse toFullDTO(Product product){
        return new ProductFullResponse(
                VendorParse.toShortDTO(product.getVendor()),
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
    public static ProductShortResponse toShortDTO(Product product){
        return new ProductShortResponse(
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

    public static Product updateByDTO(Product product, ProductUpdateRequest request){
        product.setName(Optional.ofNullable(request.name()).orElse(product.getName()));
        product.setDescription(Optional.ofNullable(request.description()).orElse(product.getDescription()));
        product.setPrice(Optional.ofNullable(request.price()).orElse(product.getPrice()));
        return product;
    }

    public static PagedModel<ProductFullResponse> toPagedModel(Page<Product> all) {
        return new PagedModel<>(all.map(ProductParse::toFullDTO));
    }
    public static PagedModel<ProductShortResponse> toPagedModelShort(Page<Product> all) {
        return new PagedModel<>(all.map(ProductParse::toShortDTO));
    }
}
