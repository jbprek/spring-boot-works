package com.foo.jdbc.empdept.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Employee {

    @Id
    private String id;
    private LocalDate hireDate;
    private String firstName;
    private String lastName;

    @ManyToOne
    private Department department;
}
