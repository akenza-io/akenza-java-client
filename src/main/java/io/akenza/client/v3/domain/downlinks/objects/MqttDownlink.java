package io.akenza.client.v3.domain.downlinks.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableMqttDownlink.class)
@JsonDeserialize(as = ImmutableMqttDownlink.class)
@AkenzaStyle
public abstract class MqttDownlink {

    public abstract Map<String, Object> payload();

    public abstract String topic();

    @Value.Default
    public DownlinkContentType contentType() {
        return DownlinkContentType.JSON;
    }
}
