package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableTagReference.class)
@JsonDeserialize(as = ImmutableTagReference.class)
public interface TagReference {
    /**
     * ID
     */
    String id();
}
