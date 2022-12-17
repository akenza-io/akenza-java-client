package io.akenza.client.v3.domain.device_types.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableDeviceTypeMetadata.class)
@JsonDeserialize(as = ImmutableDeviceTypeMetadata.class)
@AkenzaStyle
public interface DeviceTypeMetadata {
    @Nullable
    String manufacturer();

    @Nullable
    String url();

    @Nullable
    String author();

    @Nullable
    Map<String, Object> properties();

    @Nullable
    String firmwareVersion();

    @Nullable
    String encoding();

    @Nullable
    String connectivity();

    @Nullable
    List<String> availableSensors();

    @Nullable
    List<String> outputTopics();
}
