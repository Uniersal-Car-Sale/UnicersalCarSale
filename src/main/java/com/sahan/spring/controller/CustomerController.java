package com.sahan.spring.controller;

import com.sahan.spring.dto.CustomerDTO;
import com.sahan.spring.exception.BaseException;
import com.sahan.spring.exception.RecordAlreadySubmittedException;
import com.sahan.spring.exception.RecordNotFoundException;
import com.sahan.spring.service.CustomerService;
import com.sahan.spring.util.HttpCustomStatus;
import com.sahan.spring.util.StandradResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin
@Slf4j
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO dto) {
        try {
            return ResponseEntity.ok(customerService.saveCustomer(dto));
        } catch (RecordAlreadySubmittedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_ALREADY_SUBMIT).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteCustomer(@RequestParam String nic) {
        try {
            return ResponseEntity.ok(customerService.deleteCustomer(nic));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO dto) {
        try {
            return ResponseEntity.ok(customerService.updateCustomer(dto));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{nic}")
    public ResponseEntity<?> searchCustomerDetail(@PathVariable String nic) {
        try {
            return ResponseEntity.ok(customerService.searchCustomer(nic));
        } catch (RecordNotFoundException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_NOT_FOUND).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view-all")
    public ResponseEntity<?> getAllCustomerDetails() {
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
