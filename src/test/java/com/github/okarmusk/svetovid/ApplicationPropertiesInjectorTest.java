/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationPropertiesInjectorTest {
    @Test
    @DisplayName("Should inject values from application.properties to annotated primitive class fields")
    void injectValuesToAnnotatedPrimitiveTypeFields() {
        // given
        class Clazz {
            @ApplicationProperty("boolean.property")
            private boolean fieldBoolean;
            @ApplicationProperty("short.property")
            private short fieldShort;
            @ApplicationProperty("integer.property")
            private int fieldInt;
            @ApplicationProperty("long.property")
            private long fieldLong;
            @ApplicationProperty("float.property")
            private float fieldFloat;
            @ApplicationProperty("double.property")
            private double fieldDouble;

            Clazz() {
                ApplicationPropertiesInjector.inject(this);
            }
        }

        // when
        final var clazz = new Clazz();

        // then
        assertTrue(clazz.fieldBoolean);
        assertEquals(8, clazz.fieldShort);
        assertEquals(12345, clazz.fieldInt);
        assertEquals(9223372036854775807L, clazz.fieldLong);
        assertEquals(32.64f, clazz.fieldFloat);
        assertEquals(8888877777.9999007, clazz.fieldDouble);
    }

    @Test
    @DisplayName("Should inject values from application.properties to annotated class fields")
    void injectValuesToAnnotatedFields() {
        // given
        class Clazz {
            @ApplicationProperty("boolean.property")
            private Boolean fieldBoolean;
            @ApplicationProperty("short.property")
            private Short fieldShort;
            @ApplicationProperty("integer.property")
            private Integer fieldInt;
            @ApplicationProperty("long.property")
            private Long fieldLong;
            @ApplicationProperty("float.property")
            private Float fieldFloat;
            @ApplicationProperty("double.property")
            private Double fieldDouble;
            @ApplicationProperty("string.property")
            private String fieldString;

            Clazz() {
                ApplicationPropertiesInjector.inject(this);
            }
        }

        // when
        final var clazz = new Clazz();

        // then
        assertTrue(clazz.fieldBoolean);
        assertEquals((short) 8, clazz.fieldShort);
        assertEquals(12345, clazz.fieldInt);
        assertEquals(9223372036854775807L, clazz.fieldLong);
        assertEquals(32.64f, clazz.fieldFloat);
        assertEquals(8888877777.9999007, clazz.fieldDouble);
        assertEquals("foo bar baz", clazz.fieldString);
    }

    @Test
    @DisplayName("Should inject values from application.properties to annotated class fields, even from environment variable")
    void injectValuesEvenFromEnvironmentVariablesToAnnotatedFields() {
        // given
        class Clazz {
            @ApplicationProperty("java.home")
            private String javaHome;

            Clazz() {
                ApplicationPropertiesInjector.inject(this);
            }
        }

        // when
        final var clazz = new Clazz();

        // then
        assertNotNull(clazz.javaHome);
    }
}
