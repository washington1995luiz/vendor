package br.com.washington.vendor.controllers;

import br.com.washington.vendor.dto.request.VendorCreateRequest;
import br.com.washington.vendor.dto.request.VendorUpdateRequest;
import br.com.washington.vendor.dto.response.VendorFullResponse;
import br.com.washington.vendor.exception.InvalidUUIDException;
import br.com.washington.vendor.exception.VendorNotFoundException;
import br.com.washington.vendor.service.VendorService;
import br.com.washington.vendor.util.VendorParse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vendor")
public class VendorController {

    private final VendorService vendorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    VendorFullResponse create(@Valid @RequestBody VendorCreateRequest vendor){
        return VendorParse.toFullDTO(vendorService.create(vendor));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    VendorFullResponse findById(@PathVariable(value = "id") @NotNull @NotBlank String id){
        return VendorParse.toFullDTO(vendorService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    PagedModel<VendorFullResponse> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "sort", defaultValue = "desc") String sort){
        var direction = "asc".equalsIgnoreCase(sort) ? Sort.Direction.ASC : Sort.Direction.DESC ;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "id"));
        return VendorParse.toPagedModel(vendorService.findAll(pageable));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    VendorFullResponse update(@Valid @RequestBody VendorUpdateRequest vendor){
        return VendorParse.toFullDTO(vendorService.update(vendor));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable(value = "id") @NotNull @NotBlank String id){
        vendorService.delete(id);
    }
}
