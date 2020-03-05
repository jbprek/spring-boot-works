package com.foo.config;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@Data
@ConfigurationProperties(prefix="config.demo")
public class ConfigDemoProperties {

    /**
     * Simple value
     */
    @NotBlank
    private String product;

    /**
     * Example of enum in the configuration
     */
    @NotNull
    private Delivery delivery;

    /**
     * Cannot parse directly LocalDates need a converter
     */
    @NotNull
    private LocalDate date;

    /**
     * Simple List Example
     */
    @NotEmpty
    List<String> products;

    /**
     * List using a converter
     */
    @NotEmpty
    List<LocalDate> deadlines;

    /**
     * Simple map
     */
    Map<String,String> simplePairs;

    /**
     * Nested Class
     */
    @Valid
    BirthDate nested;

}
