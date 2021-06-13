package data.jpa.relationships.m2ou.empdept.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(name = "M2OU_EMP")
public class M2OUEmployee {

    @Id
    private String id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private M2OUDepartment department;
}
