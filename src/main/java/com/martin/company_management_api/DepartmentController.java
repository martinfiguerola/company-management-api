package com.martin.company_management_api;

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
    public ResponseEntity<DepartmentResponseDTO> createDepartment (@RequestBody DepartmentRequestDTO dto) {
        DepartmentResponseDTO responseDTO = departmentService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment (@PathVariable Long id, @RequestBody DepartmentRequestDTO dto) {
        Optional<DepartmentResponseDTO> responseDTO = departmentService.update(id, dto);
        return responseDTO.map(departmentResponseDTO -> ResponseEntity.status(HttpStatus.OK).body(departmentResponseDTO))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getDepartments () {
        List<DepartmentResponseDTO> responseDTOS = departmentService.findAll();
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

}
