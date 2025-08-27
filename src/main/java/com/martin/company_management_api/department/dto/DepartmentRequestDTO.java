package com.martin.company_management_api.department.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentRequestDTO {

    @NotBlank(message = "Department name cannot be blank")
    @Size(min = 3, max = 100, message = "School name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Department location cannot be blank")
    @Size(min = 5, max = 200, message = "Department location must be between 5 and 200 characters")
    private String location;
}
