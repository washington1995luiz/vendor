package br.com.washington.vendor.controllers;

import br.com.washington.vendor.dto.request.ProductCreateRequest;
import br.com.washington.vendor.dto.request.ProductUpdateRequest;
import br.com.washington.vendor.dto.response.ProductFullResponse;
import br.com.washington.vendor.dto.response.ProductShortResponse;
import br.com.washington.vendor.entities.Product;
import br.com.washington.vendor.exception.ErrorMessagesEnum;
import br.com.washington.vendor.service.ProductService;
import br.com.washington.vendor.util.ProductParse;
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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductFullResponse create(@Valid @RequestBody ProductCreateRequest product){
        return ProductParse.toFullDTO(productService.create(product));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable(value = "id") @NotNull @NotBlank String id){
        productService.delete(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    ProductFullResponse update(@RequestBody ProductUpdateRequest product){
        return ProductParse.toFullDTO(productService.update(product));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductFullResponse findById(@PathVariable(value = "id") @NotNull @NotBlank String id){
        return ProductParse.toFullDTO(productService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    PagedModel<ProductFullResponse> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "sort", defaultValue = "desc") String sort){
        var direction = "asc".equalsIgnoreCase(sort) ? Sort.Direction.ASC : Sort.Direction.DESC ;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "id"));
        return ProductParse.toPagedModel(productService.findAll(pageable));

    }

    @GetMapping(value = "/search/{vendorId}")
    @ResponseStatus(HttpStatus.OK)
    PagedModel<ProductShortResponse> findByVendorId(@PathVariable(value = "vendorId") @NotNull @NotBlank String vendorId,
                                                    @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "5") Integer size,
                                                    @RequestParam(value = "sort", defaultValue = "name") String sort,
                                                    @RequestParam(value = "direction", defaultValue = "desc") String direction){
        if(size <= 0) {
            throw new IllegalArgumentException(ErrorMessagesEnum.INVALID_PAGE_SIZE.getMessage());
        }
        if(!isValidSortField(sort)) {
            sort = "name";
        }

        Sort.Direction setDirection = Sort.Direction.fromOptionalString(direction).orElse(Sort.Direction.DESC);
        Pageable pageable = PageRequest.of(page, size, Sort.by(setDirection, sort));

        return ProductParse.toPagedModelShort(productService.findByVendorId(pageable, vendorId));
    }

    private static boolean isValidSortField(String sort) {
        return Arrays.stream(Product.class.getDeclaredFields())
                .map(Field::getName)
                .anyMatch(nameField -> nameField.equalsIgnoreCase(sort));
    }
}
