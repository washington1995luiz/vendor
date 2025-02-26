package br.com.washington.vendor.controllers.v2;

import br.com.washington.vendor.dto.response.ProductProjection;
import br.com.washington.vendor.entities.Product;
import br.com.washington.vendor.exception.ErrorMessagesEnum;
import br.com.washington.vendor.exception.InvalidUUIDException;
import br.com.washington.vendor.repositories.ProductRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/product")
public class ProductControllerV2 {

    private final ProductRepository repository;

    @GetMapping(value = "/search/{vendorId}")
    PagedModel<ProductProjection> findByVendorId(@PathVariable(value = "vendorId") @NotNull @NotBlank String vendorId,
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

        return new PagedModel<>(repository.findByVendorIdCustom(pageable, parseUUID(vendorId)));
    }
    private UUID parseUUID(String id) {
        try{
            return UUID.fromString(id);
        } catch (IllegalArgumentException _){
            throw new InvalidUUIDException(id);
        }
    }
    private static boolean isValidSortField(String sort) {
        return Arrays.stream(Product.class.getDeclaredFields())
                .map(Field::getName)
                .anyMatch(nameField -> nameField.equalsIgnoreCase(sort));
    }
}
