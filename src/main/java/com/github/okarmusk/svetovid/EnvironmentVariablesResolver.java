/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid;

import java.util.regex.Pattern;

final class EnvironmentVariablesResolver {
    private final static String ENVIRONMENT_VARIABLE_GROUP = "env";
    private final static Pattern pattern = Pattern.compile("\\$\\{(?<env>\\w+)\\}");

    /**
     *
     * @param s should contain environment variables names agreed with pattern ${ENV_VAR}
     * @return string with actual environment variable values
     * @throws EnvironmentVariableException
     */
    static String resolve(String s) throws EnvironmentVariableException {
        final var matcher = pattern.matcher(s);
        final var buffer = new StringBuffer();

        while (matcher.find()) {
            final var environmentVariableName = matcher.group(ENVIRONMENT_VARIABLE_GROUP);
            final var environmentVariable = System.getenv(environmentVariableName);

            if (environmentVariable == null) {
                throw new EnvironmentVariableException(String.format("Environment variable: %s doesn't exist.", environmentVariableName));
            }

            matcher.appendReplacement(buffer, environmentVariable);
        }

        matcher.appendTail(buffer);

        return buffer.toString();
    }
}
