package com.martin.company_management_api.employee.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.martin.company_management_api.address.model.Address;
import com.martin.company_management_api.department.model.Department;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "T_EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;

    @OneToOne(
            mappedBy = "employee",
            cascade = CascadeType.ALL
    )
    private Address address;
}
