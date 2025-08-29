package com.martin.company_management_api.employee.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeResponseDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}
