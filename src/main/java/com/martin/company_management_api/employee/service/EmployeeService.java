package com.martin.company_management_api.employee.service;

import com.martin.company_management_api.employee.dto.EmployeeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Page<EmployeeResponseDTO> findAll (Pageable pageable);
}
