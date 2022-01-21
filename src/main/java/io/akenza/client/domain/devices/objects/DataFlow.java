package io.akenza.client.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableDataFlow.class)
@JsonDeserialize(as = ImmutableDataFlow.class)
public interface DataFlow {
    String id();

    String name();

    @Nullable
    DeviceType deviceType();

    DeviceConnectorDetails deviceConnector();
}
