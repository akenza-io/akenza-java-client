package io.akenza.client.utils;

import javax.annotation.Nullable;
import java.time.Instant;

/**
 * Convenience interface for tracking creation and update times
 */
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