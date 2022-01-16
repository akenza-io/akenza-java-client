package io.akenza.client.domain.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceData.class)
@JsonDeserialize(as = ImmutableDeviceData.class)
public interface DeviceData {
    String deviceId();

    String topic();

    Map<String, Object> data();
    
    Instant timestamp();
}
