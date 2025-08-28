package com.martin.company_management_api.employee.mapper;

import com.martin.company_management_api.employee.dto.EmployeeResponseDTO;
import com.martin.company_management_api.employee.model.Employee;

public class EmployeeMapperDTO {

    public static EmployeeResponseDTO toDTO (Employee employee) {

        EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstname(employee.getFirstname());
        employeeDTO.setLastname(employee.getLastname());
        employeeDTO.setEmail(employee.getEmail());

        return employeeDTO;
    }
}
