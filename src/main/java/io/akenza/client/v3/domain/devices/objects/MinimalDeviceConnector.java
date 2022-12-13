package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMinimalDeviceConnector.class)
@JsonDeserialize(as = ImmutableMinimalDeviceConnector.class)
public interface MinimalDeviceConnector {
    String id();

    String name();
}
