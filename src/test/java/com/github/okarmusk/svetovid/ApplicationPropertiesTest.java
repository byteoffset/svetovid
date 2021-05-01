/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationPropertiesTest {
    @Test
    @DisplayName("Should contains all properties from default file")
    void getAllPropertiesFromDefaultFile() {
        // given
        final var propertyNames = Set.of("boolean.property", "short.property", "integer.property", "long.property",
                "float.property", "double.property", "string.property", "url.property", "random.property", "java.home");

        // when
        final var applicationProperties = ApplicationProperties.ofDefaultFile();

        // then
        propertyNames.forEach(propertyName -> assertNotNull(applicationProperties.getAsString(propertyName)));
    }

    @Test
    @DisplayName("Should get property as boolean")
    void getPropertyFromDefaultFileAsBoolean() {
        // given
        final var propertyName = "boolean.property";

        // when
        final var applicationProperties = ApplicationProperties.ofDefaultFile();
        final var result = applicationProperties.getAsBoolean(propertyName);

        // then
        assertTrue(result);
    }

    @Test
    @DisplayName("Should get property as short")
    void getPropertyFromDefaultFileAsShort() {
        // given
        final var propertyName = "short.property";

        // when
        final var applicationProperties = ApplicationProperties.ofDefaultFile();
        final var result = applicationProperties.getAsInt(propertyName);

        // then
        assertEquals(8, result);
    }

    @Test
    @DisplayName("Should get property as integer")
    void getPropertyFromDefaultFileAsInteger() {
        // given
        final var propertyName = "integer.property";

        // when
        final var applicationProperties = ApplicationProperties.ofDefaultFile();
        final var result = applicationProperties.getAsInt(propertyName);

        // then
        assertEquals(12345, result);
    }

    @Test
    @DisplayName("Should get property as long")
    void getPropertyFromDefaultFileAsLong() {
        // given
        final var propertyName = "long.property";

        // when
        final var applicationProperties = ApplicationProperties.ofDefaultFile();
        final var result = applicationProperties.getAsLong(propertyName);

        // then
        assertEquals(Long.MAX_VALUE, result);
    }

    @Test
    @DisplayName("Should get property as float")
    void getPropertyFromDefaultFileAsFloat() {
        // given
        final var propertyName = "float.property";

        // when
        final var applicationProperties = ApplicationProperties.ofDefaultFile();
        final var result = applicationProperties.getAsFloat(propertyName);

        // then
        assertEquals(32.64f, result);
    }

    @Test
    @DisplayName("Should get property as double")
    void getPropertyFromDefaultFileAsDouble() {
        // given
        final var propertyName = "double.property";

        // when
        final var applicationProperties = ApplicationProperties.ofDefaultFile();
        final var result = applicationProperties.getAsDouble(propertyName);

        // then
        assertEquals(8888877777.9999007, result);
    }
}
