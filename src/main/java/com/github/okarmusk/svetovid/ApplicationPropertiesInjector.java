/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

public class ApplicationPropertiesInjector {
    private final static ApplicationProperties applicationProperties = ApplicationProperties.ofFile();

    private ApplicationPropertiesInjector() { }

    /**
     * Method inject properties read from application.properties file
     *
     * @param instance is object of a class, in most cases `this`
     */
    public static void inject(Object instance) {
        final var fields = instance.getClass().getDeclaredFields();

        for (final var field : fields) {
            if (field.isAnnotationPresent(ApplicationProperty.class)) {
                final var applicationProperty = field.getAnnotation(ApplicationProperty.class);
                field.setAccessible(true);

                try {
                    final var propertyName = applicationProperty.value();
                    final var clazz = field.getType();

                    if (clazz == Integer.TYPE) {
                        final var propertyValue = applicationProperties.getAsInt(propertyName);
                        field.set(instance, propertyValue);
                    } else if (clazz == Long.TYPE) {
                        final var propertyValue = applicationProperties.getAsLong(propertyName);
                        field.set(instance, propertyValue);
                    } else if (clazz == String.class) {
                        final var propertyValue = applicationProperties.getAsString(propertyName);
                        field.set(instance, propertyValue);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
