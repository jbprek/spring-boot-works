package data.jpa.relationships.m2ou.controller;

import data.jpa.relationships.m2ou.persistence.DepartmentRepository;
import data.jpa.relationships.m2ou.persistence.Department;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/departments")
public class DepartmentsController {

    DepartmentRepository repository;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Department>> all() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path="{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> findOne(@PathVariable("id") Integer id) {
        Optional<Department> optionalDepartment = repository.findById(id);
        if (optionalDepartment.isPresent()) {
            return ResponseEntity.ok(optionalDepartment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> create(@RequestBody Department Department) {
        repository.create(Department);
        return new ResponseEntity(Department, HttpStatus.CREATED);
    }

    @DeleteMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return  ResponseEntity.accepted().build();
    }
    
//    @PatchMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Department> update(@RequestBody Department department) {
//        repository.updateDepartmentName(department.getId(), department.getName());
//        return new ResponseEntity(department, HttpStatus.OK);
//    }


}
