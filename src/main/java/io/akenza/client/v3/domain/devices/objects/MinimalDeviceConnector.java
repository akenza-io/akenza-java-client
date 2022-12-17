package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMinimalDeviceConnector.class)
@JsonDeserialize(as = ImmutableMinimalDeviceConnector.class)
@AkenzaStyle
public interface MinimalDeviceConnector {
    String id();

    String name();
}
