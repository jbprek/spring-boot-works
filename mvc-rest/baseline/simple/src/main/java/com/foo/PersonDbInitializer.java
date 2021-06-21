package com.foo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PersonDbInitializer  implements CommandLineRunner {

    private final PersonRepository repository;

    public PersonDbInitializer(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(Person.of(1L, "John", "Smith", 30));
        repository.save(Person.of(2L, "Paul", "Mavros", 35));
        repository.save(Person.of(3L, "George", "Lucan", 37));
    }
}
