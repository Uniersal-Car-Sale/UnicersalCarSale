package com.sahan.spring.service;

import com.sahan.spring.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    String saveSupplier(SupplierDTO dto);

    String updateSupplier(SupplierDTO dto);

    SupplierDTO searchSupplier(String nic);

    String deleteSupplier(String nic);

    List<SupplierDTO> getAllSuppliers();
}
