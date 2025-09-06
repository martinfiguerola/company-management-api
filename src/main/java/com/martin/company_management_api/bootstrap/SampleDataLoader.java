package com.martin.company_management_api.bootstrap;

import com.github.javafaker.Faker;
import com.martin.company_management_api.address.model.Address;
import com.martin.company_management_api.address.repository.AddressRepository;
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
    private final AddressRepository addressRepository;
    private final Faker faker;

    public SampleDataLoader(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, AddressRepository addressRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.faker = new Faker();
    }


    @Override
    public void run(String... args) throws Exception {

        if (employeeRepository.count() == 0 ){

            // 1. Create and save some departments
            Department it = new Department();
            it.setName("IT");
            it.setLocation("1st Floor");

            Department hr = new Department();
            hr.setName("HR");
            hr.setLocation("2nd Floor");

            Department finance = new Department();
            finance.setName("Finance");
            finance.setLocation("3rd Floor");

            List<Department> departments = List.of(it, hr, finance);

            // Save departments first
            departmentRepository.saveAll(departments);

            // 2. Create 40 employees and assign them to a random department
            for (int i = 0; i < 40; i++) {

                Employee employee = new Employee();
                employee.setFirstname(faker.name().firstName());
                employee.setLastname(faker.name().lastName());
                employee.setEmail(faker.internet().emailAddress());

                // assign a random department
                Department randomDepartment = departments.get(faker.number().numberBetween(0, departments.size()));
                employee.setDepartment(randomDepartment);

                // 3. Create Address
                Address address = new Address();
                address.setStreet(faker.address().streetAddress());
                address.setCity(faker.address().city());
                address.setState(faker.address().state());
                address.setZipcode(faker.address().zipCode());

                // Set bidirectionality
                employee.setAddress(address);
                address.setEmployee(employee);

                // 4. Add to department
                randomDepartment.getEmployees().add(employee);
                employeeRepository.save(employee);

                // 5. Save departments → employees and addresses are saved by cascade
                departmentRepository.saveAll(departments);

                // Log created department to confirm seeding worked
                logger.info("Seeded {} employees across {} departments.",
                        40, departments.size());

            }

        }
    }
}
