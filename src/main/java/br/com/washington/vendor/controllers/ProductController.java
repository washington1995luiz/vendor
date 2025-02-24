package br.com.washington.vendor.controllers;

import br.com.washington.vendor.dto.request.ProductCreateRequest;
import br.com.washington.vendor.dto.response.ProductFullResponse;
import br.com.washington.vendor.service.ProductService;
import br.com.washington.vendor.util.ProductParse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductFullResponse create(@Valid @RequestBody ProductCreateRequest product){
        return ProductParse.toDTO(productService.create(product));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductFullResponse findById(@PathVariable(value = "id") @NotNull @NotBlank String id){
        return ProductParse.toDTO(productService.findById(id));
    }
}
