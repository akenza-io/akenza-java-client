package io.akenza.client.domain.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.Instant;

@Value.Immutable
@JsonSerialize(as = ImmutableTimestamp.class)
@JsonDeserialize(as = ImmutableTimestamp.class)
public interface Timestamp {
    @Nullable
    Instant gt();

    @Nullable
    Instant gte();

    @Nullable
    Instant lt();

    @Nullable
    Instant lte();
}
