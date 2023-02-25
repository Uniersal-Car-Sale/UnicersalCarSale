package com.sahan.spring.service.impl;

import com.sahan.spring.dto.CustomerDTO;
import com.sahan.spring.entity.Customer;
import com.sahan.spring.exception.RecordAlreadySubmittedException;
import com.sahan.spring.exception.RecordNotFoundException;
import com.sahan.spring.repo.CustomerRepo;
import com.sahan.spring.service.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String saveCustomer(CustomerDTO dto) throws RecordAlreadySubmittedException {
        if (!repo.existsById(dto.getNic())) {
            Customer c = mapper.map(dto, Customer.class);
            repo.save(c);
            return SAVE_SUCCESSFUL;
        } else {
            throw new RecordAlreadySubmittedException(CUSTOMER_ALREADY_SUBMITTED);
        }
    }

    @Override
    public String updateCustomer(CustomerDTO dto) throws RecordNotFoundException {
        if (repo.existsById(dto.getNic())) {
            Customer c = mapper.map(dto, Customer.class);
            repo.save(c);
            return UPDATE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(CUSTOMER_NOT_FOUND);
        }
    }

    @Override
    public CustomerDTO searchCustomer(String nic) throws RecordNotFoundException {
        Optional<Customer> customer = repo.findById(nic);
        if (customer.isPresent()) {
            return mapper.map(customer.get(), CustomerDTO.class);
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + nic);
        }
    }


    @Override
    public String deleteCustomer(String nic) throws RecordNotFoundException {
        if (repo.existsById(nic)) {
            repo.deleteById(nic);
            return DELETE_SUCCESSFUL;
        } else {
            throw new RecordNotFoundException(RECORD_NOT_FOUND + nic);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<CustomerDTO>>() {
        }.getType());
    }

}

