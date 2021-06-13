package data.jpa.relationships.m2ou.empdeptdao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Table(name = "CC_DEPT")
@Entity
public class Department {

    @Id
    private Integer id;
    private String name;

}
