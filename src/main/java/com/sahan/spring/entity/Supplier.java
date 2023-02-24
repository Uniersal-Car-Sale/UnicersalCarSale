package com.sahan.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Supplier {
    @Id
    private String supNic;
    private String supName;
    private String address;
    private String email;
    private String contactNo;

}
