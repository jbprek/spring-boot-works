package com.foo.jdbc.empdept.persistence;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeShortInfoDto {
    private String id;
    private LocalDate hireDate;
    private String firstName;
    private String lastName;

}
