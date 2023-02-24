package com.sahan.spring.service.impl;

import com.sahan.spring.dto.CustomerDTO;
import com.sahan.spring.entity.Customer;
import com.sahan.spring.repo.CustomerRepo;
import com.sahan.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String saveCustomer(CustomerDTO dto) {
        if (!repo.existsById(dto.getNic())) {
            Customer c = mapper.map(dto, Customer.class);
            repo.save(c);
            return "Saved Successfully";
        } else {
            throw new RuntimeException("Customer already exist!");
        }
    }

    @Override
    public String updateCustomer(CustomerDTO dto) {
        if (repo.existsById(dto.getNic())) {
            Customer c = mapper.map(dto, Customer.class);
            repo.save(c);
            return "Updated Successfully";
        } else {
            throw new RuntimeException("No such customer to update..!");
        }
    }

    @Override
    public CustomerDTO searchCustomer(String nic) {
        Optional<Customer> customer = repo.findById(nic);
        if (customer.isPresent()) {
            return mapper.map(customer.get(), CustomerDTO.class);
        } else {
            throw new RuntimeException("No customer details for id: " + nic);
        }
    }


    @Override
    public String deleteCustomer(String nic) {
        if (repo.existsById(nic)) {
            repo.deleteById(nic);
            return "Deleted Successfully";
        } else {
            throw new RuntimeException("No customer for delete ID: " + nic);
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> all = repo.findAll();
        return mapper.map(all, new TypeToken<List<CustomerDTO>>() {
        }.getType());
    }

}

