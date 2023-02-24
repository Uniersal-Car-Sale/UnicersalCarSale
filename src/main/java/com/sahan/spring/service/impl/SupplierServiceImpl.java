package com.sahan.spring.service.impl;

import com.sahan.spring.dto.SupplierDTO;
import com.sahan.spring.entity.Supplier;
import com.sahan.spring.repo.SupplierRepo;
import com.sahan.spring.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String saveSupplier(SupplierDTO dto) {
        if (!repo.existsById(dto.getSupNic())) {
            Supplier c = mapper.map(dto, Supplier.class);
            repo.save(c);
            return "Saved Successfully";
        } else {
            throw new RuntimeException("Supplier already exist!");
        }
    }

    @Override
    public String updateSupplier(SupplierDTO dto) {
        if (repo.existsById(dto.getSupNic())) {
            Supplier c = mapper.map(dto, Supplier.class);
            repo.save(c);
            return "Updated Successfully";
        } else {
            throw new RuntimeException("No such supplier detail to update..!");
        }
    }

    @Override
    public SupplierDTO searchSupplier(String nic) {
        Optional<Supplier> supplier = repo.findById(nic);
        if (supplier.isPresent()) {
            return mapper.map(supplier.get(), SupplierDTO.class);
        } else {
            throw new RuntimeException("No supplier details for id: " + nic);
        }
    }


    @Override
    public String deleteSupplier(String nic) {
        if (repo.existsById(nic)) {
            repo.deleteById(nic);
            return "Deleted Successfully";
        } else {
            throw new RuntimeException("No supplier for delete ID: " + nic);
        }

    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<SupplierDTO>>() {
        }.getType());
    }

}

