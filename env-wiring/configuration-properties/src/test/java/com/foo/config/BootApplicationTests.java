package com.foo.config;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BootApplicationTests {

    @Autowired
    ConfigDemoProperties configDemoProperties;

    @Test
    @Order(1)
    @DisplayName("Check that the configuration ")
    void testPropertiesLoaded() {
        assertThat(configDemoProperties).isNotNull();
    }

    @Test
    @Order(2)
    void testSimpleProperty() {
        assertThat(configDemoProperties.getProduct()).isEqualTo("bullshit");
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
        assertThat(configDemoProperties.getNested().getName()).isEqualTo("John");
        assertThat(configDemoProperties.getNested().getBirthDate()).isEqualTo(LocalDate.parse("1961-12-15"));
    }



}
