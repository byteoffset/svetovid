/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesFileReaderTest {
    @Test
    @DisplayName("Should read properties file from default location")
    void readPropertiesFileFromDefaultLocation() throws IOException {
        // when
        final var propertiesReader = new PropertiesFileReader();
        final var properties = propertiesReader.read();

        // then
        assertNotNull(properties);
    }

    @Test
    @DisplayName("Should throw exception if file doesn't exist")
    void cannotReadPropertiesFromNonExistingFile() {
        // given
        final var fileName = "non-existing-file.properties";
        final var expectedExceptionMessage = String.format("File %s not found.", fileName);

        // when
        final var ioException = assertThrows(IOException.class, () -> {
            final var propertiesReader = new PropertiesFileReader();
            propertiesReader.read(fileName);
        });

        // then:
        assertEquals(expectedExceptionMessage, ioException.getMessage());
    }
}
