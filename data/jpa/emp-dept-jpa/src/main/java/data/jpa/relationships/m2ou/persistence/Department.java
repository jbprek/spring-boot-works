package data.jpa.relationships.m2ou.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Table(name = "DEPARTMENTS")
@Entity
public class Department {

    @Id
    private Integer id;
    private String name;
}
