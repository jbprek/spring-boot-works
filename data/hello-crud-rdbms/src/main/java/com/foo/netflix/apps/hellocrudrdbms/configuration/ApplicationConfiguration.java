package com.foo.netflix.apps.hellocrudrdbms.configuration;

import lombok.AllArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class ApplicationConfiguration {

    private ApplicationProperties applicationProperties;

    @Profile("!test")
    @Bean
    DataSource customerDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.hsqldb.jdbc.JDBCDriver");
        dataSourceBuilder.url(applicationProperties.getDatabase().getUrl());
        dataSourceBuilder.username(applicationProperties.getDatabase().getUserName());
        dataSourceBuilder.password(applicationProperties.getDatabase().getPassword());
        return dataSourceBuilder.build();
    }
}
