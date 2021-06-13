package data.jpa.o2mb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/o2mb/departments")
public class O2MBDepartmentsReposController {

    @Autowired
    private O2MBDepartmentRepository repository;

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
        repository.save(Department);
        return new ResponseEntity(Department, HttpStatus.CREATED);
    }

    @DeleteMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return  ResponseEntity.accepted().build();
    }

    @PatchMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> update(@RequestBody Department Department) {
        repository.save(Department);
        return new ResponseEntity(Department, HttpStatus.CREATED);
    }


}
