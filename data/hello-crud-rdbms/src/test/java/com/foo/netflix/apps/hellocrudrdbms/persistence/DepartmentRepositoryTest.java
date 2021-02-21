package com.foo.netflix.apps.hellocrudrdbms.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository repository;

    @Test
    public void findSavedEmployeeById() {
        var department = Department.of(1, "IT");
        repository.save(department);
        assertThat(repository.findById(department.getId())).hasValue(department);
    }

    @Test
    public void findUpdateEmployeeById() {
        var department = Department.of(1, "IT");
        repository.save(department);
        department.setName("IT Division+");
        repository.save(department);
        assertThat(repository.findById(department.getId())).hasValue(department);
    }

    @Test
    public void checkDeletedEmployeeById() {
        var department = Department.of(1, "IT");
        repository.save(department);
        repository.deleteById(1);
        assertThat(repository.findById(1)).isEmpty();
    }

}