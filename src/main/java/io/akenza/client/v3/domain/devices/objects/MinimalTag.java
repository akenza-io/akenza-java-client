package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableMinimalTag.class)
@JsonDeserialize(as = ImmutableMinimalTag.class)
@AkenzaStyle
public interface MinimalTag extends Audited {
    String id();

    String name();

    @Nullable
    String description();
}
