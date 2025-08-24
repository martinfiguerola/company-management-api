package com.martin.company_management_api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentRequestDTO {
    private String name;
    private String location;
}
