package com.martin.company_management_api.department.service;

import com.martin.company_management_api.department.mapper.DepartmentMapperDTO;
import com.martin.company_management_api.department.dto.DepartmentRequestDTO;
import com.martin.company_management_api.department.dto.DepartmentResponseDTO;
import com.martin.company_management_api.department.model.Department;
import com.martin.company_management_api.department.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponseDTO save(DepartmentRequestDTO departmentRequestDTO) {
        // Converts the provided DepartmentRequestDTO into a Department entity
        Department departmentEntity = DepartmentMapperDTO.fromDTO(departmentRequestDTO);
        Department savedDepartment = departmentRepository.save(departmentEntity);

        // Convert the saved entity into a DTO and return it as the response
        return DepartmentMapperDTO.toDTO(savedDepartment);

    }

    @Override
    public Optional<DepartmentResponseDTO> update(Long id, DepartmentRequestDTO departmentRequestDTO) {

        // Verify that the department exists
        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        // If the department exists, update it; otherwise return an empty Optional
        return optionalDepartment.map(department -> {
            department.setName(departmentRequestDTO.getName());
            department.setLocation(departmentRequestDTO.getLocation());

            Department updatedDepartment = departmentRepository.save(department);

            return DepartmentMapperDTO.toDTO(updatedDepartment);
        });
    }

    @Override
    public Page<DepartmentResponseDTO> findAll(Pageable pageable) {
        // Retrieves all departments in a paginated format and maps them to response DTOs.

        // Fetch a paginated list of Department entities from the database
        Page<Department> departments = departmentRepository.findAll(pageable);
        // Convert each Department entity to a DepartmentResponseDTO
        return departments.map(DepartmentMapperDTO::toDTO);



    }

    @Override
    public Optional<DepartmentResponseDTO> findById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.map(DepartmentMapperDTO::toDTO);
    }

    @Override
    public List<DepartmentResponseDTO> findByName(String name) {
        List<Department> departments = departmentRepository.findByNameContainingIgnoreCase(name);
        return departments.stream()
                .map(DepartmentMapperDTO::toDTO)
                .toList();
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        return optionalDepartment.map(department -> {
            departmentRepository.delete(department);
            return true;
        }).orElse(false);
    }

}
