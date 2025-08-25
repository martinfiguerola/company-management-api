package com.martin.company_management_api;

import com.github.javafaker.Faker;
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
    private final Faker faker;

    public SampleDataLoader(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
        this.faker = new Faker();
    }


    @Override
    public void run(String... args) throws Exception {

        if (departmentRepository.count() == 0){

            List<Department> departments = new ArrayList<>();

            for (int i = 0; i < 40; i++) {
                Department department = new Department();
                department.setName(faker.name().fullName());
                department.setLocation(faker.number().numberBetween(1, 10) + "° Floor");

                departments.add(department);
            }

            departmentRepository.saveAll(departments);

            // Log created users to confirm seeding worked
            logger.info("Seeded {} departments.", departments.size());


        }
    }
}
