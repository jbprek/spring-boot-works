package data.jpa.relationships.m2ou.empdept.control.dao;

import data.jpa.relationships.m2ou.empdept.entity.M2OUEmployee;
import data.jpa.relationships.m2ou.empdept.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/m2ou/empdept/dao/employees")
public class EmployeesDaoController {

    EmployeeRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<M2OUEmployee>> all() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path="{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<M2OUEmployee> findOne(@PathVariable("id") String id) {
        Optional<M2OUEmployee> optionalEmployee = repository.findById(id);
        if (optionalEmployee.isPresent()) {
            return ResponseEntity.ok(optionalEmployee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<M2OUEmployee> create(@RequestBody M2OUEmployee employee) {
        repository.save(employee);
        return new ResponseEntity(employee, HttpStatus.CREATED);
    }

    @DeleteMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        repository.deleteById(id);
        return  ResponseEntity.accepted().build();
    }

    @PatchMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<M2OUEmployee> update(@RequestBody M2OUEmployee employee) {
        repository.save(employee);
        return new ResponseEntity(employee, HttpStatus.CREATED);
    }


}
