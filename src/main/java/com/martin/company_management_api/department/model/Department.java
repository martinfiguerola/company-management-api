package com.martin.company_management_api.department.model;

import com.martin.company_management_api.employee.model.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_DEPARTMENT")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;

    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.ALL
    )
    private List<Employee> employees = new ArrayList<>();
}
