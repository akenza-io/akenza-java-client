package io.akenza.client.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceType.class)
@JsonDeserialize(as = ImmutableDeviceType.class)
public interface DeviceType {
    String id();

    String name();
}
