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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final VendorService vendorService;

    @Override
    public Product create(ProductCreateRequest product) {
        Vendor vendor = vendorService.findById(product.vendor());
        return productRepository.save(ProductParse.createByDTO(product, vendor));
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(parseUUID(id)).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByVendorId(Pageable pageable, String vendorId) {
        return productRepository.findByVendorId(pageable, parseUUID(vendorId));
    }

    private UUID parseUUID(String id) {
        try{
            return UUID.fromString(id);
        } catch (IllegalArgumentException _){
            throw new InvalidUUIDException(id);
        }
    }
}
