package com.sp.inventory.repository;

import com.sp.inventory.model.SupplierProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProduct, Long> {

    @Query(
            value = """
            SELECT * from supplier_product
            where product_id  = :productId
            and   supplier_id = :supplierId
            """,
            nativeQuery = true
    )
    Optional<SupplierProduct> findSupplierProduct(
            @Param("productId")  Long productId,
            @Param("supplierId") Long supplierId
    );

}
