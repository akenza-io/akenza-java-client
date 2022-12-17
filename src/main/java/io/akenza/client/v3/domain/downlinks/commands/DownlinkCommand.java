package io.akenza.client.v3.domain.downlinks.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.downlinks.objects.LoRaDownlink;
import io.akenza.client.v3.domain.downlinks.objects.MqttDownlink;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableDownlinkCommand.class)
@JsonDeserialize(as = ImmutableDownlinkCommand.class)
@AkenzaStyle
public abstract class DownlinkCommand {
    @Nullable
    public abstract LoRaDownlink loraDownlink();

    @Nullable
    public abstract MqttDownlink mqttDownlink();

    /**
     * If raw is true, the downlink encoder will not be invoked
     */
    @Value.Default
    public Boolean raw() {
        return false;
    }
}
