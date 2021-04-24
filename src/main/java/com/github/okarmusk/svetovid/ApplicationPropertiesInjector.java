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
     * @param instance is object where injection has to be done
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

                    field.set(instance, getPropertyValue(propertyName, clazz));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Object getPropertyValue(String propertyName, Class<?> clazz) {
        if (clazz == boolean.class || clazz == Boolean.class) {
            return applicationProperties.getAsBoolean(propertyName);
        } else if (clazz == short.class || clazz == Short.class) {
            return applicationProperties.getAsShort(propertyName);
        } else if (clazz == int.class || clazz == Integer.class) {
            return applicationProperties.getAsInt(propertyName);
        } else if (clazz == long.class || clazz == Long.class) {
            return applicationProperties.getAsLong(propertyName);
        } else if (clazz == float.class || clazz == Float.class) {
            return applicationProperties.getAsFloat(propertyName);
        } else if (clazz == double.class || clazz == Double.class) {
            return applicationProperties.getAsDouble(propertyName);
        } else {
            return applicationProperties.getAsString(propertyName);
        }
    }
}
