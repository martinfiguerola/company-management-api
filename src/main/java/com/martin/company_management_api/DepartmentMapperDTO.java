package com.martin.company_management_api;

public class DepartmentMapperDTO {

    public static Department fromDTO (DepartmentRequestDTO departmentRequestDTO) {
        Department department = new Department();
        department.setName(departmentRequestDTO.getName());
        department.setLocation(departmentRequestDTO.getLocation());

        return department;
    }

    public static DepartmentResponseDTO toDTO (Department department) {
        DepartmentResponseDTO departmentDTO = new DepartmentResponseDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setLocation(department.getLocation());

        return departmentDTO;
    }
}
