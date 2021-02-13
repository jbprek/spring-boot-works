package com.foo.service.persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Pre-load some data using a Spring Boot {@link CommandLineRunner}.
 */
@Component
public class DatabaseLoader {

	/**
	 * Use Spring to inject a {@link EmployeeRepository} that can then load data. Since this will run only after the app
	 * is operational, the database will be up.
	 *
	 * @param repository
	 */
	@Bean
	CommandLineRunner init(EmployeeRepository repository) {

		return args -> {
			repository.save(new Employee("Frodo", "Baggins", "ring bearer"));
			repository.save(new Employee("Bilbo", "Baggins", "burglar"));
		};
	}

}
