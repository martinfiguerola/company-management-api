package com.martin.company_management_api.address.dto;

import com.martin.company_management_api.employee.dto.EmployeeRefDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDetailDTO {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private EmployeeRefDTO employee;

}
