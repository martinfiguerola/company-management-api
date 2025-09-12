package com.martin.company_management_api.employee.service;

import com.martin.company_management_api.employee.dto.EmployeeDetailDTO;
import com.martin.company_management_api.employee.dto.EmployeeRequestDTO;
import com.martin.company_management_api.employee.dto.EmployeeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Page<EmployeeResponseDTO> findAll (Pageable pageable);
    Optional<EmployeeDetailDTO> findById (Long id);
    List<EmployeeResponseDTO> findByName (String firstname);
    Boolean deleteById (Long id);
    EmployeeResponseDTO save (EmployeeRequestDTO employeeRequestDTO);
    Optional<EmployeeResponseDTO> update (Long id, EmployeeRequestDTO employeeRequestDTO);

}
