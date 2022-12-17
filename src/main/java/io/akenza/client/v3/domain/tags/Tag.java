package io.akenza.client.v3.domain.tags;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Audited;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableTag.class)
@JsonDeserialize(as = ImmutableTag.class)
@AkenzaStyle
public interface Tag extends Audited {
    String id();

    String name();

    @Nullable
    String description();

    String workspaceId();

    @Nullable
    String color();

    /**
     * the number of tagged device
     */
    long taggedDevices();
}
