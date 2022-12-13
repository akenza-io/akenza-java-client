package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMinimalDeviceType.class)
@JsonDeserialize(as = ImmutableMinimalDeviceType.class)
public interface MinimalDeviceType {
    String id();

    String name();
}
