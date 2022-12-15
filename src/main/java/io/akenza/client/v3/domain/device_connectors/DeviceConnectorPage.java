package io.akenza.client.v3.domain.device_connectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Page;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceConnectorPage.class)
@JsonDeserialize(as = ImmutableDeviceConnectorPage.class)
@AkenzaStyle
public abstract class DeviceConnectorPage implements Page<DeviceConnector> {
    public abstract List<DeviceConnector> content();
}
