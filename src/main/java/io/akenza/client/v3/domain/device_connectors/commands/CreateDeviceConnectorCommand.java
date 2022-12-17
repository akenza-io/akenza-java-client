package io.akenza.client.v3.domain.device_connectors.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Preconditions;
import io.akenza.client.utils.AkenzaStyle;
import io.akenza.client.v3.domain.common.Connectivity;
import io.akenza.client.v3.domain.device_connectors.objects.AuthType;
import io.akenza.client.v3.domain.device_connectors.objects.DecodingType;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Immutable
@JsonSerialize(as = ImmutableCreateDeviceConnectorCommand.class)
@JsonDeserialize(as = ImmutableCreateDeviceConnectorCommand.class)
@AkenzaStyle
public abstract class CreateDeviceConnectorCommand {
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
     * Workspace ID
     */
    public abstract String workspaceId();

    /**
     * Auth type of the device connector (BASIC or DEVICE_CREDENTIALS)
     */
    public abstract AuthType authType();

    /**
     * Connectivity of the connector
     */
    public abstract Connectivity connectivity();

    /**
     * Whether to push back device configuration automatically during connection (only supported for MQTT devices)
     */
    @Nullable
    public abstract Boolean pushConfigurationAutomatically();

    /**
     * Whether this device connector uses a special decoding logic.
     */
    @Nullable
    @Value.Default
    public DecodingType decodingType() {
        return DecodingType.NONE;
    }

    //TODO add support for Yanzi device connectors

    @Value.Check
    protected void check() {
        Preconditions.checkState(!connectivity().equals(Connectivity.LORA), "LoRa connectors can only be created via integrations.");
    }
}
