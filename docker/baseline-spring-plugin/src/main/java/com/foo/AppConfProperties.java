package com.foo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Component
@Validated
@Data
@ConfigurationProperties(prefix="com.foo")
public class AppConfProperties {

    @NotBlank
    private String helloMessage;

    @NotBlank
    private String secret;

}
