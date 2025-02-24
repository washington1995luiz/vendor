package br.com.washington.vendor.repositories;

import br.com.washington.vendor.entities.Vendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    @NonNull
    Page<Vendor> findAll(@NonNull Pageable pageable);
}
