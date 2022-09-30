package com.ros.inventory.Repository;

import com.ros.inventory.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    Supplier getBySupplierId(UUID id);

}
