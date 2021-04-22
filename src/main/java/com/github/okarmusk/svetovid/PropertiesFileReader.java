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
    Properties readProperties() throws IOException {
        return read(DEFAULT_PROPERTIES_FILE_NAME);
    }

    /**
     * Read properties from given file
     *
     * @param fileName name of file to read the properties from
     * @return Properties
     * @throws IOException thrown if file not found
     */
    Properties readProperties(String fileName) throws IOException {
        return read(fileName);
    }

    private Properties read(String fileName) throws IOException {
        final var inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(fileName);

        final var properties = new Properties();

        if (inputStream == null) {
            throw new FileNotFoundException(String.format("File %s not found.", fileName));
        }

        properties.load(inputStream);

        return properties;
    }
}
