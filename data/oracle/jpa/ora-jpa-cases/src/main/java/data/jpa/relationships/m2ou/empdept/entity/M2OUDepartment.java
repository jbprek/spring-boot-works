package data.jpa.relationships.m2ou.empdept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Table(name = "M2OU_DEPT")
@Entity
public class M2OUDepartment {

    @Id
    private Integer id;
    private String name;

}
