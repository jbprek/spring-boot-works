package com.foo.config;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ConfigDemoPropertiesTest {

    @Autowired
    ConfigDemoProperties configDemoProperties;

    @Test
    @Order(1)
    @DisplayName("Check that the configuration ")
    void testPropertiesLoaded() {
        assertThat(configDemoProperties).isNotNull();
        System.out.println(configDemoProperties);
    }

    @Test
    @Order(2)
    void testSimpleProperty() {
        assertThat(configDemoProperties.getProduct()).isEqualTo("Very Marvelous");
    }

    @Test
    @Order(3)
    void testEnumValue() {
        assertThat(configDemoProperties.getDelivery()).isEqualTo(Delivery.DEV);
    }

    @Test
    @Order(4)
    void testListValue() {
        assertThat(configDemoProperties.getProducts()).isEqualTo(Arrays.asList("tomatos","cucumber","other"));
    }

    @Test
    @Order(5)
    void testListOfLocaDate() {
        assertThat(configDemoProperties.getDeadlines()).isEqualTo(Arrays.asList(LocalDate.parse("2020-02-16"),LocalDate.parse("2020-02-17")));
    }

    @Test
    @Order(6)
    void testSimpleMap() {
        Map<String, String> expectedValues = new HashMap<>();
        expectedValues.put("key1", "value1");
        expectedValues.put("key2", "value2");
        assertThat(configDemoProperties.getSimplePairs()).containsExactlyInAnyOrderEntriesOf(expectedValues);
    }

    @Test
    @Order(7)
    void testNested() {
        assertThat(configDemoProperties.getBirthData().getName()).isEqualTo("John");
        assertThat(configDemoProperties.getBirthData().getDateOfBirth()).isEqualTo(LocalDate.parse("1961-12-15"));
    }

}