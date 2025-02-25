package br.com.washington.vendor.repositories;

import br.com.washington.vendor.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @NonNull
    Page<Product> findAll(@NonNull Pageable pageable);

    Page<Product> findByVendorId(@NonNull Pageable pageable, @NonNull UUID vendor);
}
