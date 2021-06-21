package com.foo;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/persons")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Iterable<Person>> all() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Person> findById(@PathVariable("id")Long id) {
        var optPerson = repository.findById(id);
        if ( optPerson.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optPerson.get());
    }

    @PostMapping( consumes = "application/json")
    public ResponseEntity create(@RequestBody Person person) {
        repository.save(person);
        return ResponseEntity.created(null).build();
    }





}
