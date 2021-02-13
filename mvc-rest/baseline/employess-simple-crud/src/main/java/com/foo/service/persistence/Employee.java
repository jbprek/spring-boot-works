package com.foo.service.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Employee {

	@Id @GeneratedValue private Long id;
	private String firstName;
	private String lastName;
	private String role;

	/**
	 * Useful constructor when id is not yet known.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param role
	 */
	Employee(String firstName, String lastName, String role) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
}
