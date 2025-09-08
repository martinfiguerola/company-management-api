package com.martin.company_management_api.employee.dto;

import com.martin.company_management_api.address.dto.AddressRefDTO;
import com.martin.company_management_api.address.model.Address;
import com.martin.company_management_api.department.dto.DepartmentRefDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDetailDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private DepartmentRefDTO department;
    private AddressRefDTO address;
}
