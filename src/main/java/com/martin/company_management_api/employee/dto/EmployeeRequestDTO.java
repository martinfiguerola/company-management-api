package com.martin.company_management_api.employee.dto;

import com.martin.company_management_api.department.model.Department;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeRequestDTO {
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 3, max = 40, message = "First name must be between 2 and 40 characters")
    private String firstname;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 3, max = 40, message = "Last name must be between 2 and 40 characters")
    private String lastname;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email must be a valid format")
    private String email;

    private Long department;
}
