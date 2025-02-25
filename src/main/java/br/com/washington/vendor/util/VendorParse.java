package br.com.washington.vendor.util;

import br.com.washington.vendor.dto.request.VendorCreateRequest;
import br.com.washington.vendor.dto.request.VendorUpdateRequest;
import br.com.washington.vendor.dto.response.VendorFullResponse;
import br.com.washington.vendor.dto.response.VendorShortResponse;
import br.com.washington.vendor.entities.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;

import java.util.Objects;
import java.util.Optional;

public class VendorParse {

    public static Vendor createByDTO(VendorCreateRequest request) {
        return Vendor.builder()
                .name(request.name())
                .address(request.address())
                .state(request.state())
                .city(request.city())
                .country(request.country())
                .zip(request.zip())
                .build();
    }
    public static Vendor updateByDTO(Vendor vendor, VendorUpdateRequest request) {
        vendor.setName(Optional.ofNullable(request.name()).orElse(vendor.getName()));
        vendor.setAddress(Optional.ofNullable(request.address()).orElse(vendor.getAddress()));
        vendor.setState(Optional.ofNullable(request.state()).orElse(vendor.getState()));
        vendor.setCity(Optional.ofNullable(request.city()).orElse(vendor.getCity()));
        vendor.setCountry(Optional.ofNullable(request.country()).orElse(vendor.getCountry()));
        vendor.setZip(Optional.ofNullable(request.zip()).orElse(vendor.getZip()));
        return vendor;
    }

    public static VendorFullResponse toFullDTO(Vendor vendor){
        if(Objects.isNull(vendor)){ return null; }
        return new VendorFullResponse(
                vendor.getId(),
                vendor.getName(),
                vendor.getAddress(),
                vendor.getCity(),
                vendor.getState(),
                vendor.getZip(),
                vendor.getCountry()
        );
    }

    public static VendorShortResponse toShortDTO(Vendor vendor){
        if(Objects.isNull(vendor)){ return null; }
        return new VendorShortResponse(
                vendor.getId(),
                vendor.getName()
        );
    }

    public static PagedModel<VendorFullResponse> toPagedModel(Page<Vendor> all) {
       return new PagedModel<>(all.map(VendorParse::toFullDTO));
    }
}
