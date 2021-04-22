/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid.exceptions;

public class EnvironmentVariableNotFoundException extends Exception {
    public EnvironmentVariableNotFoundException(String message) {
        super(message);
    }
}
