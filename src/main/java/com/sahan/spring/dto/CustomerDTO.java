package com.sahan.spring.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String cusName;
    private String address;
    private String nic;
    private String email;
    private String contactNo;

}
