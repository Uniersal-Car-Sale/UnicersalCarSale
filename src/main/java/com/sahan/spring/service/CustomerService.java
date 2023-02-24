package com.sahan.spring.service;

import com.sahan.spring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerDTO dto);

    String updateCustomer(CustomerDTO dto);

    CustomerDTO searchCustomer(String nic);

    String deleteCustomer(String nic);

    List<CustomerDTO> getAllCustomers();
}
