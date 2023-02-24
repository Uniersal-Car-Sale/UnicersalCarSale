package com.sahan.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private String supNic;
    private String supName;
    private String address;
    private String email;
    private String contactNo;

}
