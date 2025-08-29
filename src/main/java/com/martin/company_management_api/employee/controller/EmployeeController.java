package com.martin.company_management_api.employee.controller;

import com.martin.company_management_api.employee.dto.EmployeeRequestDTO;
import com.martin.company_management_api.employee.dto.EmployeeResponseDTO;
import com.martin.company_management_api.employee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDTO>> getEmployees (Pageable pageable) {
        Page<EmployeeResponseDTO> responseDTOPage = employeeService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOPage);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee (@PathVariable Long id) {
        Optional<EmployeeResponseDTO> responseDTOOptional = employeeService.findById(id);

        return responseDTOOptional.map(employeeResponseDTO -> ResponseEntity.status(HttpStatus.OK).body(employeeResponseDTO))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/search/{employee-name}")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeeByName (@PathVariable("employee-name") String firstname) {
        List<EmployeeResponseDTO> responseDTOS = employeeService.findByName(firstname);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee (@PathVariable Long id) {
        if (employeeService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with given ID does not exist.");
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee (@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO responseDTO = employeeService.save(employeeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee (@PathVariable Long id, @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        Optional<EmployeeResponseDTO> optionalEmployeeResponseDTO = employeeService.update(id, employeeRequestDTO);
        return optionalEmployeeResponseDTO
                .map(employeeResponseDTO -> ResponseEntity.status(HttpStatus.OK).body(employeeResponseDTO))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
