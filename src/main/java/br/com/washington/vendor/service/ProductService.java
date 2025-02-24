package br.com.washington.vendor.service;

import br.com.washington.vendor.dto.request.ProductCreateRequest;
import br.com.washington.vendor.entities.Product;

public interface ProductService {


    Product create(ProductCreateRequest product);

    Product findById(String id);
}
