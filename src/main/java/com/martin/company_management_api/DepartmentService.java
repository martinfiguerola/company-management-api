package com.martin.company_management_api;

import java.util.Optional;

public interface DepartmentService {

    DepartmentResponseDTO save (DepartmentRequestDTO departmentRequestDTO);
    Optional<DepartmentResponseDTO> update (Long id, DepartmentRequestDTO departmentRequestDTO);
}
