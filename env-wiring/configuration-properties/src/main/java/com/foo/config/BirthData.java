package com.foo.config;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * Example class for nested use in Configuration Properties
 */
@Data
public class BirthData {
    @NotBlank
    private String name;
    @Past
    private LocalDate dateOfBirth;

}
