package data.jpa.relationships.m2ou.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    private String id;
    private LocalDate hireDate;
    private String firstName;
    private String lastName;

    @ManyToOne
    private Department department;
}
