package data.jpa.relationships.m2ou.empdeptdao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(name = "CC_EMP")
public class Employee {

    @Id
    private String id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
}
