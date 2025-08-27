package com.martin.company_management_api.department.repository;

import com.martin.company_management_api.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Retrieves a list of departments whose names contain the given value (case-insensitive)
    List<Department> findByNameContainingIgnoreCase (String name);
}
