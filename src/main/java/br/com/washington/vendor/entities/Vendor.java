package br.com.washington.vendor.entities;

import br.com.washington.vendor.dto.request.VendorCreateRequest;
import br.com.washington.vendor.dto.request.VendorUpdateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vendors")
@Entity
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vend_id")
    private UUID id;

    @Column(name = "vend_name", nullable = false, length = 100)
    private String name;

    @Column(name = "vend_address")
    private String address;

    @Column(name = "vend_city", length = 100)
    private String city;

    @Column(name = "vend_state", length = 50)
    private String state;

    @Column(name = "vend_zip", length = 20)
    private String zip;

    @Column(name = "vend_country", length = 50)
    private String country;
}
