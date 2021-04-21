/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

public class Configuration {
    private final String defaultConfigFileName = "application.properties";

    private Configuration() { }

    /**
     * Read configuration from default location (resources/application.properties)
     *
     * @return Configuration object
     */
    public static Configuration fromResources() {
        return null;
    }

    /**
     * Read configuration from given path
     *
     * @param path to configuration file
     * @return Configuration object
     */
    public static Configuration fromPath(String path) {
        return null;
    }
}
