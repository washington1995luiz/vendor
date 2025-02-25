package br.com.washington.vendor.service.impl;

import br.com.washington.vendor.dto.request.ProductCreateRequest;
import br.com.washington.vendor.dto.request.ProductUpdateRequest;
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
        return productRepository.save(ProductParse.createByDTO(product, vendorService.findById(product.vendor())));
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

    @Override
    public void delete(String id) {
        productRepository.delete(this.findById(id));
    }

    @Override
    public Product update(ProductUpdateRequest product) {
        return productRepository.save(ProductParse.updateByDTO(this.findById(product.id()), product));
    }

    private UUID parseUUID(String id) {
        try{
            return UUID.fromString(id);
        } catch (IllegalArgumentException _){
            throw new InvalidUUIDException(id);
        }
    }
}
