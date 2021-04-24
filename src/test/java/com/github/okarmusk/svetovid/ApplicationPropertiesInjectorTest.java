/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationPropertiesInjectorTest {
    @Test
    @DisplayName("Should inject values from application.properties to annotated class fields")
    void injectValuesToAnnotatedFields() {
        // given
        class InjectionTest {
            @ApplicationProperty("test.integer.property")
            private int fieldInt;
            @ApplicationProperty("test.long.property")
            private long fieldLong;
            @ApplicationProperty("test.string.property")
            String fieldString;
            @ApplicationProperty("test.java.home")
            private String javaHome;

            InjectionTest() {
                ApplicationPropertiesInjector.inject(this);
            }
        }

        // when
        final var injectionTest = new InjectionTest();

        // then
        assertEquals(12345, injectionTest.fieldInt);
        assertEquals(9223372036854775807L, injectionTest.fieldLong);
        assertEquals("foo bar baz", injectionTest.fieldString);
        assertNotNull(injectionTest.javaHome);
    }
}
