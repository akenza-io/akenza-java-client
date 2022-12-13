package io.akenza.client.v3.domain.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import java.time.Instant;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceData.class)
@JsonDeserialize(as = ImmutableDeviceData.class)
@AkenzaStyle
public interface DeviceData {
    @JsonAlias("deviceId")
    String akenzaDeviceId();

    String topic();

    Map<String, Object> data();

    Instant timestamp();
}
