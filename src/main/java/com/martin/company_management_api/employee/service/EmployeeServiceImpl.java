package com.martin.company_management_api.employee.service;

import com.martin.company_management_api.employee.dto.EmployeeResponseDTO;
import com.martin.company_management_api.employee.mapper.EmployeeMapperDTO;
import com.martin.company_management_api.employee.model.Employee;
import com.martin.company_management_api.employee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Page<EmployeeResponseDTO> findAll(Pageable pageable) {

        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        return employeePage.map(EmployeeMapperDTO::toDTO); // Return the employees dto page
    }
}
