package io.akenza.client.v3.domain.data_flows.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceConnectorReference.class)
@JsonDeserialize(as = ImmutableDeviceConnectorReference.class)
@AkenzaStyle
public interface DeviceConnectorReference {
    /**
     * ID
     */
    String id();
}
