package com.martin.company_management_api.department.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentResponseDTO {
    private Long id;
    private String name;
    private String location;
}
