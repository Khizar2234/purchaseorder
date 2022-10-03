package com.ros.inventory.Repository;

import com.ros.inventory.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    Supplier getBySupplierId(UUID id);

    @Query(value="SELECT * FROM supplier WHERE supplier_name = :supplier_name",nativeQuery=true)
    Supplier  getByName(@Param("supplier_name") String supplier_name);
}
