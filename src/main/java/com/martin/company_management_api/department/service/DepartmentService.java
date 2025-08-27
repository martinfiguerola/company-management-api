package com.martin.company_management_api.department.service;

import com.martin.company_management_api.department.dto.DepartmentRequestDTO;
import com.martin.company_management_api.department.dto.DepartmentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    DepartmentResponseDTO save (DepartmentRequestDTO departmentRequestDTO);
    Optional<DepartmentResponseDTO> update (Long id, DepartmentRequestDTO departmentRequestDTO);
    Page<DepartmentResponseDTO> findAll (Pageable pageable);
    Optional<DepartmentResponseDTO> findById (Long id);
    List<DepartmentResponseDTO> findByName (String name);
    Boolean deleteById (Long id);

}
