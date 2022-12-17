package io.akenza.client.v3.domain.downlinks.objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableLoRaDownlink.class)
@JsonDeserialize(as = ImmutableLoRaDownlink.class)
@AkenzaStyle
public abstract class LoRaDownlink {
    @Value.Default
    public int port() {
        return 1;
    }

    @Nullable
    public abstract String payloadHex();

    @Value.Default
    public Boolean confirmed() {
        return false;
    }

    @Nullable
    public abstract Map<String, Object> payload();

    public abstract boolean clearQueue();
}
