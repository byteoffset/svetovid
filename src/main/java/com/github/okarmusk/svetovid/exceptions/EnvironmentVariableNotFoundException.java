/*
 * Copyright 2021 Konrad Okarmus
 */

package com.github.okarmusk.svetovid.exceptions;

public class EnvironmentVariableNotFoundException extends Exception {
    private static final long serialVersionUID = -8197149150520447029L;

    public EnvironmentVariableNotFoundException(String message) {
        super(message);
    }
}
