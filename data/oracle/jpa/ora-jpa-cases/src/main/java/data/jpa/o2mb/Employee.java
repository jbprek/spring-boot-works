package data.jpa.o2mb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Entity
@Table(name = "EMP")
public class Employee {

    @Id
    private String id;
    private String name;

    /* Solution 1, display full department, EAGER Fetch is needed */
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="DEPARTMENT_ID")
//    private Department department;

    /* Solution 2, display only  department Id , Works with LAZY  Fetch */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DEPARTMENT_ID")
    private Department department;

    @JsonGetter("department")
    public  Integer geDisplayDepartment(){
        Department dpt = this.getDepartment();
        if (dpt == null){
            return -1;
        }
        return this.getDepartment().getId();
    }

}
