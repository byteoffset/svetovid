/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

import com.github.okarmusk.svetovid.exceptions.EnvironmentVariableNotFoundException;

import java.io.IOException;
import java.util.Properties;

public final class ApplicationProperties {
    private final static PropertiesReader propertiesReader = new PropertiesReader();
    private final Properties properties;

    private ApplicationProperties(Properties properties) {
        this.properties = properties;
    }

    public static ApplicationProperties getFromDefaultFile() {
        try {
            final var properties = resolveEnvironmentVariables(propertiesReader.readProperties());

            return new ApplicationProperties(properties);
        } catch (IOException | EnvironmentVariableNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static ApplicationProperties getFromFile(String fileName) {
        try {
            final var properties = resolveEnvironmentVariables(propertiesReader.readProperties(fileName));

            return new ApplicationProperties(properties);
        } catch (IOException | EnvironmentVariableNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Properties resolveEnvironmentVariables(Properties properties) throws EnvironmentVariableNotFoundException {
        final var propertyNames = properties.stringPropertyNames();

        for (final var propertyName : propertyNames) {
            final var propertyValue = EnvironmentVariablesResolver.resolve(properties.getProperty(propertyName));
            properties.setProperty(propertyName, propertyValue);
        }

        return properties;
    }

    /**
     * Get value as string
     *
     * @param propertyName property name
     * @return stored value as string
     */
    public String getAsString(String propertyName) {
        return properties.getProperty(propertyName);
    }

    /**
     * Get value as integer
     *
     * @param propertyName property name
     * @return stored value as int
     */
    public int getAsInt(String propertyName) {
        return Integer.parseInt(properties.getProperty(propertyName));
    }

    /**
     * Get value as long
     *
     * @param propertyName property name
     * @return stored value as long
     */
    public long getAsLong(String propertyName) {
        return Long.parseLong(properties.getProperty(propertyName));
    }
}
