package io.akenza.client.utils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

/**
 * Convenience interface for tracking versions
 */
@Value.Immutable
@JsonSerialize(as = ImmutableAudited.class)
@JsonDeserialize(as = ImmutableAudited.class)
public interface Versioned {

    /**
     * Version of the entity
     *
     * @return The version of the object
     */
    @Nullable
    Integer version();
}