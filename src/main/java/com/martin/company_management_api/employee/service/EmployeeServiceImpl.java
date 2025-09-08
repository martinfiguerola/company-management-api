package com.martin.company_management_api.employee.service;

import com.martin.company_management_api.employee.dto.EmployeeDetailDTO;
import com.martin.company_management_api.employee.dto.EmployeeRequestDTO;
import com.martin.company_management_api.employee.dto.EmployeeResponseDTO;
import com.martin.company_management_api.employee.mapper.EmployeeMapperDTO;
import com.martin.company_management_api.employee.model.Employee;
import com.martin.company_management_api.employee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<EmployeeResponseDTO> findAll(Pageable pageable) {

        Page<Employee> employeePage = employeeRepository.findAll(pageable);

        return employeePage.map(EmployeeMapperDTO::toDTO); // Return the employees dto page
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<EmployeeDetailDTO> findById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        // Convert the optionalEmployee to employeeDTO with departmentRef and addressRef
        return optionalEmployee.map(EmployeeMapperDTO::toEmployeeDetailDTO);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeResponseDTO> findByName(String firstname) {
        List<Employee> employees = employeeRepository.findByFirstnameContainingIgnoreCase(firstname);

        return employees.stream()
                .map(EmployeeMapperDTO::toDTO)
                .toList();
    }

    @Transactional
    @Override
    public Boolean deleteById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        return optionalEmployee.map(employee -> {
            employeeRepository.delete(employee);
            return true;
        }).orElse(false);
    }

    @Transactional
    @Override
    public EmployeeResponseDTO save(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = EmployeeMapperDTO.fromDTO(employeeRequestDTO);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapperDTO.toDTO(savedEmployee);

    }

    @Transactional
    @Override
    public Optional<EmployeeResponseDTO> update(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        return optionalEmployee.map(employee -> {
            employee.setFirstname(employeeRequestDTO.getFirstname());
            employee.setLastname(employeeRequestDTO.getLastname());
            employee.setEmail(employeeRequestDTO.getEmail());

            Employee updatedEmployee = employeeRepository.save(employee);

            return EmployeeMapperDTO.toDTO(updatedEmployee);
        });
    }


}
