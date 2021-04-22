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
        final var propertyNames = Set.of("test.integer.property", "test.long.property", "test.string.property",
                "test.url.property", "test.random.property");

        // when
        final var applicationProperties = ApplicationProperties.getFromDefaultFile();

        // then
        propertyNames.forEach(propertyName -> assertNotNull(applicationProperties.getAsString(propertyName)));
    }

    @Test
    @DisplayName("Should get property as integer")
    void getPropertyFromDefaultFileAsInteger() {
        // given
        final var propertyName = "test.integer.property";

        // when
        final var applicationProperties = ApplicationProperties.getFromDefaultFile();
        final var result = applicationProperties.getAsInt(propertyName);

        // then
        assertEquals(12345, result);
    }

    @Test
    @DisplayName("Should get property as long")
    void getPropertyFromDefaultFileAsLong() {
        // given
        final var propertyName = "test.long.property";

        // when
        final var applicationProperties = ApplicationProperties.getFromDefaultFile();
        final var result = applicationProperties.getAsLong(propertyName);

        // then
        assertEquals(Long.MAX_VALUE, result);
    }

    @Test
    @DisplayName("Should fail if trying to read non existing file")
    void failIfReadNonExistingFile() {
        // given
        final var fileName = "non-existing-file.properties";
        final var expectedExceptionMessage = String.format("File %s not found.", fileName);

        // when
        final var exception = assertThrows(RuntimeException.class,
                () -> ApplicationProperties.getFromFile(fileName));

        // then
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }
}
