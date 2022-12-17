package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableMinimalDeviceType.class)
@JsonDeserialize(as = ImmutableMinimalDeviceType.class)
@AkenzaStyle
public interface MinimalDeviceType {
    String id();

    String name();
}
