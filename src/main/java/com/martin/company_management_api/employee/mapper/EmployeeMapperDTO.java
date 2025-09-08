package com.martin.company_management_api.employee.mapper;

import com.martin.company_management_api.address.dto.AddressRefDTO;
import com.martin.company_management_api.address.mapper.AddressMapperDTO;
import com.martin.company_management_api.address.model.Address;
import com.martin.company_management_api.department.dto.DepartmentRefDTO;
import com.martin.company_management_api.department.mapper.DepartmentMapperDTO;
import com.martin.company_management_api.department.model.Department;
import com.martin.company_management_api.employee.dto.EmployeeDetailDTO;
import com.martin.company_management_api.employee.dto.EmployeeRequestDTO;
import com.martin.company_management_api.employee.dto.EmployeeResponseDTO;
import com.martin.company_management_api.employee.model.Employee;

public class EmployeeMapperDTO {

    public static EmployeeDetailDTO toEmployeeDetailDTO (Employee employee) {
        EmployeeDetailDTO employeeDetailDTO = new EmployeeDetailDTO();
        employeeDetailDTO.setId(employee.getId());
        employeeDetailDTO.setFirstname(employee.getFirstname());
        employeeDetailDTO.setLastname(employee.getLastname());
        employeeDetailDTO.setEmail(employee.getEmail());

        Department department = employee.getDepartment();
        DepartmentRefDTO departmentRefDTO = DepartmentMapperDTO.departmentRefDTO(department);

        employeeDetailDTO.setDepartment(departmentRefDTO);

        Address address = employee.getAddress();
        AddressRefDTO addressRefDTO = AddressMapperDTO.addressRefDTO(address);

        employeeDetailDTO.setAddress(addressRefDTO);

        return employeeDetailDTO;
    }

    public static EmployeeResponseDTO toDTO (Employee employee) {

        EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstname(employee.getFirstname());
        employeeDTO.setLastname(employee.getLastname());
        employeeDTO.setEmail(employee.getEmail());

        return employeeDTO;
    }

    public static Employee fromDTO (EmployeeRequestDTO employeeRequestDTO) {

        Employee employee = new Employee();
        employee.setFirstname(employeeRequestDTO.getFirstname());
        employee.setLastname(employeeRequestDTO.getLastname());
        employee.setEmail(employeeRequestDTO.getEmail());

        return employee;
    }
}
