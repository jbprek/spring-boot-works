package data.jpa.relationships.m2ou.empdept.control.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/m2ou/empdept/dao/departments")
public class DepartmentsDaoController {

    @Autowired
    private DepartmentDao dao;
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DepartmentDto>> all() {
        return ResponseEntity.ok(dao.getAllDepartments());
    }

    @GetMapping(path="{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDto> findOne(@PathVariable("id") Integer id) {
        Optional<DepartmentDto> optionalDepartment = dao.findDepartment(id);
        if (optionalDepartment.isPresent()) {
            return ResponseEntity.ok(optionalDepartment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Department> create(@RequestBody Department Department) {
//        repository.save(Department);
//        return new ResponseEntity(Department, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
//        repository.deleteById(id);
//        return  ResponseEntity.accepted().build();
//    }
//
//    @PatchMapping(consumes =  MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Department> update(@RequestBody Department Department) {
//        repository.save(Department);
//        return new ResponseEntity(Department, HttpStatus.CREATED);
//    }


}
