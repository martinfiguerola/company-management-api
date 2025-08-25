package com.martin.company_management_api;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    DepartmentResponseDTO save (DepartmentRequestDTO departmentRequestDTO);
    Optional<DepartmentResponseDTO> update (Long id, DepartmentRequestDTO departmentRequestDTO);
    List<DepartmentResponseDTO> findAll ();
    Optional<DepartmentResponseDTO> findById (Long id);
    List<DepartmentResponseDTO> findByName (String name);

}
