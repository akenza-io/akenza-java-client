package io.akenza.client.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceConnectorDetails.class)
@JsonDeserialize(as = ImmutableDeviceConnectorDetails.class)
public interface DeviceConnectorDetails {
    String id();

    String name();
}
