package br.com.washington.vendor.service.impl;

import br.com.washington.vendor.dto.request.VendorCreateRequest;
import br.com.washington.vendor.dto.request.VendorUpdateRequest;
import br.com.washington.vendor.entities.Vendor;
import br.com.washington.vendor.exception.InvalidUUIDException;
import br.com.washington.vendor.exception.ObjectIsNullException;
import br.com.washington.vendor.exception.VendorNotFoundException;
import br.com.washington.vendor.repositories.VendorRepository;
import br.com.washington.vendor.service.ProductService;
import br.com.washington.vendor.service.VendorService;
import br.com.washington.vendor.util.VendorParse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public final class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public Vendor create(VendorCreateRequest vendor) {
        isNull(vendor);
        return vendorRepository.save(VendorParse.createByDTO(vendor));
    }

    @Override
    public Vendor findById(String id) {
        return vendorRepository.findById(parseUUID(id)).orElseThrow(() -> new VendorNotFoundException(id));
    }

    @Override
    public Page<Vendor> findAll(Pageable pageable) {
        return vendorRepository.findAll(pageable);
    }

    @Override
    public Vendor update(VendorUpdateRequest vendor) {
        isNull(vendor);
        return vendorRepository.save(VendorParse.updateByDTO(this.findById(vendor.id()), vendor));
    }

    @Override
    public void delete(String id) {
        vendorRepository.delete(this.findById(id));
    }

    private UUID parseUUID(String id) {
        try{
            return UUID.fromString(id);
        } catch (IllegalArgumentException _){
            throw new InvalidUUIDException(id);
        }
    }

    private void isNull(VendorCreateRequest vendor){
        if(Objects.isNull(vendor)){
            throw new ObjectIsNullException();
        }
    }
    private void isNull(VendorUpdateRequest vendor){
        if(Objects.isNull(vendor)){
            throw new ObjectIsNullException();
        }
    }


}
