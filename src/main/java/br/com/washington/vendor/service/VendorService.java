package br.com.washington.vendor.service;

import br.com.washington.vendor.dto.request.VendorCreateRequest;
import br.com.washington.vendor.dto.request.VendorUpdateRequest;
import br.com.washington.vendor.entities.Vendor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

import java.util.UUID;

public interface VendorService {

    Vendor create(VendorCreateRequest vendor);

    Vendor findById(String id);

    Page<Vendor> findAll(Pageable pageable);

    Vendor update(VendorUpdateRequest vendor);

    void delete(String id);
}
