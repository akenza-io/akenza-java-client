package io.akenza.client.v3.domain.device_connectors.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.akenza.client.utils.AkenzaStyle;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableUpdateDeviceConnectorCommand.class)
@JsonDeserialize(as = ImmutableUpdateDeviceConnectorCommand.class)
@AkenzaStyle
public abstract class UpdateDeviceConnectorCommand {
    /**
     * ID
     */
    public abstract String id();

    /**
     * Name
     */
    public abstract String name();

    /**
     * Description
     */
    @Nullable
    public abstract String description();

    /**
     * Whether to push back device configuration automatically during connection (only supported for MQTT devices)
     */
    @Nullable
    public abstract Boolean pushConfigurationAutomatically();

}
