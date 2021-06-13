package data.jpa.o2mb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Table(name = "DEPT")
@Entity
public class Department {

    @Id
    private Integer id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    List<Employee> employeeList;

}
