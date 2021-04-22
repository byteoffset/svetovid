/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

import com.github.okarmusk.svetovid.exceptions.EnvironmentVariableNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnvironmentVariablesResolverTest {
    @Test
    @DisplayName("When no environment variables, should parse input without errors")
    void parseInputWithoutEnvironmentVariables() throws EnvironmentVariableNotFoundException {
        // given
        final var randomPropertyValue = "(*iTu0Afj987(*YU (*Y adf [kqw(*L p['IJ \baef\\";

        // when
        final var resultPropertyValue = EnvironmentVariablesResolver.resolve(randomPropertyValue);

        // then
        assertEquals(randomPropertyValue, resultPropertyValue);
    }

    @Test
    @DisplayName("Should find environment variable pattern and throw exception")
    void parseInputAndThrowException() {
        // given
        final var propertyWithEnvironmentVariablePattern = "${UYNIUYGDWS_TYAYSDG7}";

        // when
        final var environmentVariableNotFoundException = assertThrows(EnvironmentVariableNotFoundException.class,
                () -> EnvironmentVariablesResolver.resolve(propertyWithEnvironmentVariablePattern));

        // then
        final var expectedMessage = "Environment variable: UYNIUYGDWS_TYAYSDG7 doesn't exist.";
        final var receivedMessage = environmentVariableNotFoundException.getMessage();

        assertEquals(expectedMessage, receivedMessage);
    }

    @Test
    @DisplayName("Should find environment variable pattern and replace it with value")
    void parseInputAndReplaceEnvironmentVariableWithItsValue() throws EnvironmentVariableNotFoundException {
        // given
        final var javaHomePattern = "${JAVA_HOME}";
        final var propertyWithJavaHomeEnvironmentVariable = "xyz${JAVA_HOME}*&//\\${JAVA_HOME}";

        // when
        final var propertyValue = EnvironmentVariablesResolver.resolve(propertyWithJavaHomeEnvironmentVariable);

        // then
        assertNotEquals(propertyWithJavaHomeEnvironmentVariable, propertyValue);
        assertFalse(propertyValue.contains(javaHomePattern));
    }
}
