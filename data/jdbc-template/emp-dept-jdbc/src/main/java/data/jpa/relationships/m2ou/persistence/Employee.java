package data.jpa.relationships.m2ou.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Employee {

    private String id;
    private LocalDate hireDate;
    private String firstName;
    private String lastName;

    private Department department;
}
