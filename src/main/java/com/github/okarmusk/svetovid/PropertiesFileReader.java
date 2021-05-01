/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

final class PropertiesFileReader {
    private final static String DEFAULT_PROPERTIES_FILE_NAME = "application.properties";

    /**
     * Read properties from default application.properties file
     *
     * @return Properties
     * @throws IOException thrown if file not found
     */
    Properties read() throws IOException {
        return readPropertiesFile(DEFAULT_PROPERTIES_FILE_NAME);
    }

    /**
     * Read properties from given file
     *
     * @return Properties
     * @throws IOException thrown if file not found
     */
    Properties read(String fileName) throws IOException {
        return readPropertiesFile(fileName);
    }

    private Properties readPropertiesFile(String fileName) throws IOException {
        try (var inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            final var properties = new Properties();

            if (inputStream == null) {
                throw new FileNotFoundException(String.format("File %s not found.", fileName));
            }

            properties.load(inputStream);

            return properties;
        }
    }
}
