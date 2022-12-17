package io.akenza.client.utils;

import javax.annotation.Nullable;

/**
 * Convenience interface for tracking versions
 */
public interface Versioned {

    /**
     * Version of the entity
     *
     * @return The version of the object
     */
    @Nullable
    Integer version();
}