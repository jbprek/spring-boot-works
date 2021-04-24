package com.foo.jdbc.empdept.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Department {

    @Id
    private Integer id;
    private String name;
}
