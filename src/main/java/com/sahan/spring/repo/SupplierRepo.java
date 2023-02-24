package com.sahan.spring.repo;

import com.sahan.spring.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier, String> {

}
