package com.foo.config;

import java.time.LocalDate;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Example of use of a Converter for ConfigProperties
 */
@Component
@ConfigurationPropertiesBinding
public class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String s) {
        return LocalDate.parse(s);
    }
}
