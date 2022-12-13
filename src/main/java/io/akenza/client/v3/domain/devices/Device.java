package io.akenza.client.v3.domain.devices;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.v3.domain.assets.Asset;
import io.akenza.client.v3.domain.devices.objects.Connectivity;
import io.akenza.client.v3.domain.devices.objects.DeviceOnlineState;
import io.akenza.client.v3.domain.devices.objects.LoRaProperties;
import io.akenza.client.v3.domain.devices.objects.MinimalDataFlow;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableDevice.class)
@JsonDeserialize(as = ImmutableDevice.class)
public interface Device extends Asset {
    String deviceId();

    @Nullable
    Map<String, Object> properties();

    Connectivity connectivity();

    Integer onlineTimeout();

    /**
     * Online
     * only available on get requests
     */
    @Nullable
    Boolean online();

    /**
     * Online State
     * only available on get requests
     */
    @Nullable
    DeviceOnlineState onlineState();

    @Nullable
    Boolean registered();

    String dataFlowId();

    /**
     * Data Flow
     * only available on get requests
     */
    @Nullable
    MinimalDataFlow dataFlow();

    @Nullable
    LoRaProperties loraProperties();
}
