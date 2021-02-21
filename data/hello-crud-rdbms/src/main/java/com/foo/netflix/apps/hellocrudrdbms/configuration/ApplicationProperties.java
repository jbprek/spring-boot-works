package com.foo.netflix.apps.hellocrudrdbms.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Component
@ConfigurationProperties(prefix="com.foo.netflix.apps.hellocrudrdbms")
public class ApplicationProperties {

    @Data
    public static class DatabaseParameters {
        private String url;
        private String userName;
        private String password;
    }

    private DatabaseParameters database;


}
