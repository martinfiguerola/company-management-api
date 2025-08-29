package com.martin.company_management_api.bootstrap;

import com.github.javafaker.Faker;
import com.martin.company_management_api.department.model.Department;
import com.martin.company_management_api.department.repository.DepartmentRepository;
import com.martin.company_management_api.employee.model.Employee;
import com.martin.company_management_api.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SampleDataLoader.class);
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final Faker faker;

    public SampleDataLoader(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.faker = new Faker();
    }


    @Override
    public void run(String... args) throws Exception {

        if (departmentRepository.count() == 0 ){

            List<Department> departments = new ArrayList<>();

            for (int i = 0; i < 40; i++) {
                Department department = new Department();
                department.setName(faker.name().fullName());
                department.setLocation(faker.number().numberBetween(1, 10) + "° Floor");

                departments.add(department);
            }

            departmentRepository.saveAll(departments);

            // Log created department to confirm seeding worked
            logger.info("Seeded {} departments.", departments.size());

        }

        if (employeeRepository.count() == 0) {

            List<Employee> employees = new ArrayList<>();

            for (int i = 0; i < 40; i++){
                Employee employee = new Employee();
                employee.setFirstname(faker.name().firstName());
                employee.setLastname(faker.name().lastName());
                employee.setEmail(faker.internet().emailAddress());

                employees.add(employee);
            }

            employeeRepository.saveAll(employees);

            // Log created employee to confirm seeding worked
            logger.info("Seeded {} employees.", employees.size());



        }
    }
}
