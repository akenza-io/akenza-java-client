package io.akenza.client.utils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.Instant;

/**
 * Convenience interface for tracking creation and update times
 */
@Value.Immutable
@JsonSerialize(as = ImmutableAudited.class)
@JsonDeserialize(as = ImmutableAudited.class)
public interface Audited {

    /**
     * Created date
     *
     * @return The date when the entity was created
     */
    @Nullable
    Instant created();

    /**
     * Updated date
     *
     * @return The date when the entity was updated
     */
    @Nullable
    Instant updated();
}