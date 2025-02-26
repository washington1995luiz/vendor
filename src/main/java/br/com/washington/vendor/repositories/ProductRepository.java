package br.com.washington.vendor.repositories;

import br.com.washington.vendor.dto.response.ProductProjection;
import br.com.washington.vendor.entities.Product;
import br.com.washington.vendor.entities.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @NonNull
    Page<Product> findAll(@NonNull Pageable pageable);

    Page<Product> findByVendorId(@NonNull Pageable pageable, @NonNull UUID vendor);

    @Query("SELECT p.id AS id, p.name AS name, p.description AS description, p.price AS price FROM Product p WHERE p.vendor.id = :vendorId")
    Page<ProductProjection> findByVendorIdCustom(@NonNull Pageable pageable, @NonNull UUID vendorId);

}
