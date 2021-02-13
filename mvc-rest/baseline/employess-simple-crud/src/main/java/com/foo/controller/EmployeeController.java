package com.foo.controller;

import com.foo.service.persistence.Employee;
import com.foo.service.persistence.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping(path="/employees", consumes = "application/json", produces = "application/json")
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> findAll() {
        List<Employee> results = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }

    @PostMapping
    public ResponseEntity<?> newEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = repository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findOne(@PathVariable long id) {

        return repository.findById(id) //
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable long id) {

        Employee employeeToUpdate = employee;
        employeeToUpdate.setId(id);
        repository.save(employeeToUpdate);

        return ResponseEntity.noContent().build();
    }

}
