package com.martin.company_management_api.employee.mapper;

import com.martin.company_management_api.employee.dto.EmployeeRequestDTO;
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

    public static Employee fromDTO (EmployeeRequestDTO employeeRequestDTO) {

        Employee employee = new Employee();
        employee.setFirstname(employeeRequestDTO.getFirstname());
        employee.setLastname(employeeRequestDTO.getLastname());
        employee.setEmail(employeeRequestDTO.getEmail());

        return employee;
    }
}
