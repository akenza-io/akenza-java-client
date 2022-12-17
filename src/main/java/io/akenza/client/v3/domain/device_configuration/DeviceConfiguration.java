package io.akenza.client.v3.domain.device_configuration;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.utils.Versioned;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceConfiguration.class)
@JsonDeserialize(as = ImmutableDeviceConfiguration.class)
@AkenzaStyle
public interface DeviceConfiguration extends Versioned {
    /**
     * the akenza device id
     */
    String deviceId();

    String workspaceId();

    Map<String, Object> configuration();

    Instant created();
}
