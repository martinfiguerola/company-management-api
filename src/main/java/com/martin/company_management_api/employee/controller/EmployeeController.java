package com.martin.company_management_api.employee.controller;

import com.martin.company_management_api.employee.dto.EmployeeResponseDTO;
import com.martin.company_management_api.employee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDTO>> getEmployees (Pageable pageable) {
        Page<EmployeeResponseDTO> responseDTOPage = employeeService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOPage);
    }
}
