package io.akenza.client.v3.domain.data.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.time.Instant;

@Value.Immutable
@JsonSerialize(as = ImmutableTimestamp.class)
@JsonDeserialize(as = ImmutableTimestamp.class)
@AkenzaStyle
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
