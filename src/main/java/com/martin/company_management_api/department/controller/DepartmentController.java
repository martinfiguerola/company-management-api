package com.martin.company_management_api.department.controller;

import com.martin.company_management_api.department.service.DepartmentService;
import com.martin.company_management_api.department.dto.DepartmentRequestDTO;
import com.martin.company_management_api.department.dto.DepartmentResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment (@Valid @RequestBody DepartmentRequestDTO dto) {
        DepartmentResponseDTO responseDTO = departmentService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment (@PathVariable Long id, @Valid @RequestBody DepartmentRequestDTO dto) {
        Optional<DepartmentResponseDTO> responseDTO = departmentService.update(id, dto);
        return responseDTO.map(departmentResponseDTO -> ResponseEntity.status(HttpStatus.OK).body(departmentResponseDTO))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentResponseDTO>> getDepartments (Pageable pageable) {
        Page<DepartmentResponseDTO> responseDTOS = departmentService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartment (@PathVariable Long id) {
        Optional<DepartmentResponseDTO> dtoOptional = departmentService.findById(id);

        return dtoOptional
                .map(departmentResponseDTO -> ResponseEntity.status(HttpStatus.OK).body(departmentResponseDTO))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/search/{department-name}")
    public ResponseEntity<List<DepartmentResponseDTO>> getDepartmentByName (@PathVariable("department-name") String name) {
        List<DepartmentResponseDTO> responseDTOS = departmentService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment (@PathVariable Long id) {
        if (departmentService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department with given ID does not exist.");
    }

}
