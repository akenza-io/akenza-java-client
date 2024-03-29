package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableTagReference.class)
@JsonDeserialize(as = ImmutableTagReference.class)
@AkenzaStyle
public interface TagReference {
    /**
     * ID
     */
    String id();
}
