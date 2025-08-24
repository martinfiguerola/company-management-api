package com.martin.company_management_api;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponseDTO save(DepartmentRequestDTO departmentRequestDTO) {
        // Convertir el DTO recibido a una entidad departamento
        Department departmentEntity = DepartmentMapperDTO.fromDTO(departmentRequestDTO);

        // Persistir la entidad en la base de datos
        Department savedDepartment = departmentRepository.save(departmentEntity);

        // Convertir la entidad guardada a DTO y devolverla para la respuesta
        return DepartmentMapperDTO.toDTO(savedDepartment);

    }

    @Override
    public Optional<DepartmentResponseDTO> update(Long id, DepartmentRequestDTO departmentRequestDTO) {

        // Primero verificamos que el department exista
        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        // Si existe lo actualizamos, sino devolvemos un empty optional
        return optionalDepartment.map(department -> {

            department.setName(departmentRequestDTO.getName());
            department.setLocation(departmentRequestDTO.getLocation());

            Department updatedDepartment = departmentRepository.save(department);

            return DepartmentMapperDTO.toDTO(updatedDepartment);
        });
    }

}
