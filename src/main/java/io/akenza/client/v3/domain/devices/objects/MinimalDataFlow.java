package io.akenza.client.v3.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableMinimalDataFlow.class)
@JsonDeserialize(as = ImmutableMinimalDataFlow.class)
@AkenzaStyle
public interface MinimalDataFlow {
    String id();

    String name();

    @Nullable
    MinimalDeviceType deviceType();

    MinimalDeviceConnector deviceConnector();
}
