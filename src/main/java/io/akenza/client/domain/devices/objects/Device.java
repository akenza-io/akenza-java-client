package io.akenza.client.domain.devices.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.domain.assets.objects.Asset;
import io.akenza.client.domain.devices.objects.enums.Connectivity;
import io.akenza.client.domain.devices.objects.enums.DeviceOnlineState;
import org.immutables.value.Value;

import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableDevice.class)
@JsonDeserialize(as = ImmutableDevice.class)
public interface Device extends Asset {
    String deviceId();

    Map<String, Object> properties();

    Connectivity connectivity();

    Integer onlineTimeout();

    /**
     * Online
     * only available on get requests
     */
    Boolean online();

    /**
     * Online State
     * only available on get requests
     */
    DeviceOnlineState onlineState();

    Boolean registered();

    String dataFlowId();

    /**
     * Data Flow
     * only available on get requests
     */
    DataFlow dataFlow();

    LoRaProperties loraProperties();
}
