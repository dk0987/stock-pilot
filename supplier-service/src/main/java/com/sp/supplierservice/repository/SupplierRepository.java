package com.sp.supplierservice.repository;

import com.sp.supplierservice.model.Supplier;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier , UUID> {
    boolean existsByName(String name);
    Supplier findByName(String name);
}
