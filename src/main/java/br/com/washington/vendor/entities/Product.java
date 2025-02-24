package br.com.washington.vendor.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "prod_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "vend_id", nullable = false)
    private Vendor vendor;

    @Column(name = "prod_name", length = 100, nullable = false)
    private String name;

    @Column(name = "prod_desc", columnDefinition = "TEXT")
    private String description;

    @Column(name = "prod_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

}
