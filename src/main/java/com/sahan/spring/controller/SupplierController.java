package com.sahan.spring.controller;

import com.sahan.spring.dto.SupplierDTO;
import com.sahan.spring.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/supplier")
@CrossOrigin
@Slf4j
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping("/")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDTO dto) {
        try {
            return ResponseEntity.ok(supplierService.saveSupplier(dto));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteSupplier(@RequestParam String nic) {
        try {
            return ResponseEntity.ok(supplierService.deleteSupplier(nic));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updateSupplier(@RequestBody SupplierDTO dto) {
        try {
            return ResponseEntity.ok(supplierService.updateSupplier(dto));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{nic}")
    public ResponseEntity<?> searchSupplierDetail(@PathVariable String nic) {
        try {
            return ResponseEntity.ok(supplierService.searchSupplier(nic));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view-all")
    public ResponseEntity<?> getAllSupplierDetails() {
        try {
            return ResponseEntity.ok(supplierService.getAllSuppliers());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
