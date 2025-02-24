package br.com.washington.vendor.service;

import br.com.washington.vendor.dto.request.ProductCreateRequest;
import br.com.washington.vendor.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {


    Product create(ProductCreateRequest product);

    Product findById(String id);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByVendorId(Pageable pageable, String vendorId);
}
