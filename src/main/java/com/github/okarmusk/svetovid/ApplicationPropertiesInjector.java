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

                    if (clazz == boolean.class || clazz == Boolean.class) {
                        final var propertyValue = applicationProperties.getAsBoolean(propertyName);
                        field.set(instance, propertyValue);
                    } else if (clazz == short.class || clazz == Short.class) {
                        final var propertyValue = applicationProperties.getAsShort(propertyName);
                        field.set(instance, propertyValue);
                    } else if (clazz == int.class || clazz == Integer.class) {
                        final var propertyValue = applicationProperties.getAsInt(propertyName);
                        field.set(instance, propertyValue);
                    } else if (clazz == long.class || clazz == Long.class) {
                        final var propertyValue = applicationProperties.getAsLong(propertyName);
                        field.set(instance, propertyValue);
                    } else if (clazz == float.class || clazz == Float.class) {
                        final var propertyValue = applicationProperties.getAsFloat(propertyName);
                        field.set(instance, propertyValue);
                    } else if (clazz == double.class || clazz == Double.class) {
                        final var propertyValue = applicationProperties.getAsDouble(propertyName);
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
