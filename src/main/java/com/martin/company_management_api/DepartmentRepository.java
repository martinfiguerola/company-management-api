package com.martin.company_management_api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Query para traer por el nombre
    List<Department> findByNameContainingIgnoreCase (String name);
}
