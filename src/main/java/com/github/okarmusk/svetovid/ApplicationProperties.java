/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

import com.github.okarmusk.svetovid.exceptions.EnvironmentVariableNotFoundException;

import java.io.IOException;
import java.util.Properties;

final class ApplicationProperties {
    private final static PropertiesFileReader propertiesFileReader = new PropertiesFileReader();
    private final Properties properties;

    private ApplicationProperties(Properties properties) {
        this.properties = properties;
    }

    static ApplicationProperties ofFile() {
        try {
            final var properties = resolveEnvironmentVariables(propertiesFileReader.readProperties());

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

    boolean getAsBoolean(String propertyName) {
        return Boolean.parseBoolean(properties.getProperty(propertyName));
    }

    short getAsShort(String propertyName) {
        return Short.parseShort(properties.getProperty(propertyName));
    }

    int getAsInt(String propertyName) {
        return Integer.parseInt(properties.getProperty(propertyName));
    }

    long getAsLong(String propertyName) {
        return Long.parseLong(properties.getProperty(propertyName));
    }

    float getAsFloat(String propertyName) {
        return Float.parseFloat(properties.getProperty(propertyName));
    }

    double getAsDouble(String propertyName) {
        return Double.parseDouble(properties.getProperty(propertyName));
    }

    String getAsString(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
