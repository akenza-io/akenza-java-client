package io.akenza.client.utils;

import javax.annotation.CheckForNull;

public final class Preconditions {
    private Preconditions() {
        throw new UnsupportedOperationException("Preconditions is utility class and should not be instantiated");
    }

    public static void checkState(boolean expression, @CheckForNull Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }
}
