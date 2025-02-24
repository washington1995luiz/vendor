package br.com.washington.vendor.service.impl;

import br.com.washington.vendor.dto.request.ProductCreateRequest;
import br.com.washington.vendor.entities.Product;
import br.com.washington.vendor.entities.Vendor;
import br.com.washington.vendor.exception.InvalidUUIDException;
import br.com.washington.vendor.exception.ProductNotFoundException;
import br.com.washington.vendor.repositories.ProductRepository;
import br.com.washington.vendor.service.ProductService;
import br.com.washington.vendor.service.VendorService;
import br.com.washington.vendor.util.ProductParse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final VendorService vendorService;

    @Override
    public Product create(ProductCreateRequest product) {
        Vendor vendor = vendorService.findById(product.vendorId());
        return productRepository.save(ProductParse.createByDTO(product, vendor));
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(parseUUID(id)).orElseThrow(ProductNotFoundException::new);
    }

    private UUID parseUUID(String id) {
        try{
            return UUID.fromString(id);
        } catch (IllegalArgumentException _){
            throw new InvalidUUIDException(id);
        }
    }
}
