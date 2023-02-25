package com.sahan.spring.service.impl;

import com.sahan.spring.dto.SupplierDTO;
import com.sahan.spring.entity.Supplier;
import com.sahan.spring.exception.RecordAlreadySubmittedException;
import com.sahan.spring.exception.RecordNotFoundException;
import com.sahan.spring.repo.SupplierRepo;
import com.sahan.spring.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.sahan.spring.constant.ExceptionMessageConstant.*;
import static com.sahan.spring.constant.AppConstant.*;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String saveSupplier(SupplierDTO dto) throws RecordAlreadySubmittedException {
        if (!repo.existsById(dto.getSupNic())) {
            Supplier c = mapper.map(dto, Supplier.class);
            repo.save(c);
            return SAVE_SUCCESSFUL;
        } else {
            throw new RecordAlreadySubmittedException(SUPPLIER_ALREADY_SUBMITTED);
        }
    }

    @Override
    public String updateSupplier(SupplierDTO dto) throws RecordNotFoundException {
        if (repo.existsById(dto.getSupNic())) {
            Supplier c = mapper.map(dto, Supplier.class);
            repo.save(c);
            return UPDATE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(SUPPLIER_NOT_FOUND);
        }
    }

    @Override
    public SupplierDTO searchSupplier(String nic) throws RecordNotFoundException {
        Optional<Supplier> supplier = repo.findById(nic);
        if (supplier.isPresent()) {
            return mapper.map(supplier.get(), SupplierDTO.class);
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + nic);
        }
    }


    @Override
    public String deleteSupplier(String nic) throws RecordNotFoundException {
        if (repo.existsById(nic)) {
            repo.deleteById(nic);
            return DELETE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + nic);
        }

    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<SupplierDTO>>() {
        }.getType());
    }

}

