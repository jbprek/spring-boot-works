package com.foo.jdbc.empdept.controller;

import com.foo.jdbc.empdept.persistence.Employee;
import com.foo.jdbc.empdept.persistence.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/employees")
public class EmployeesController {

    EmployeeRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> all() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path="{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> findOne(@PathVariable("id") String id) {
        Optional<Employee> optionalEmployee = repository.findById(id);
        if (optionalEmployee.isPresent()) {
            return ResponseEntity.ok(optionalEmployee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        repository.save(employee);
        return new ResponseEntity(employee, HttpStatus.CREATED);
    }

    @DeleteMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        repository.deleteById(id);
        return  ResponseEntity.accepted().build();
    }

    @PatchMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> update(@RequestBody Employee employee) {
        repository.save(employee);
        return new ResponseEntity(employee, HttpStatus.CREATED);
    }


}
