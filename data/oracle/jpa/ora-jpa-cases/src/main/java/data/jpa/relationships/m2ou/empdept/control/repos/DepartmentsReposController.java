package data.jpa.relationships.m2ou.empdept.control.repos;

import data.jpa.relationships.m2ou.empdept.entity.M2OUDepartment;
import data.jpa.relationships.m2ou.empdept.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/m2ou/repos/departments")
public class DepartmentsReposController {

    @Autowired
    private DepartmentRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<M2OUDepartment>> all() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path="{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<M2OUDepartment> findOne(@PathVariable("id") Integer id) {
        Optional<M2OUDepartment> optionalDepartment = repository.findById(id);
        if (optionalDepartment.isPresent()) {
            return ResponseEntity.ok(optionalDepartment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<M2OUDepartment> create(@RequestBody M2OUDepartment Department) {
        repository.save(Department);
        return new ResponseEntity(Department, HttpStatus.CREATED);
    }

    @DeleteMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return  ResponseEntity.accepted().build();
    }

    @PatchMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<M2OUDepartment> update(@RequestBody M2OUDepartment Department) {
        repository.save(Department);
        return new ResponseEntity(Department, HttpStatus.CREATED);
    }


}
